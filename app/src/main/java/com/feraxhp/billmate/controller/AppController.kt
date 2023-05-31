package com.feraxhp.billmate.controller

import android.content.Context
import androidx.room.Room
import com.feraxhp.billmate.logic_database.User
import com.feraxhp.billmate.logic_database.database.MyDataBase
import com.feraxhp.billmate.logic_database.database.entities.Categories
import com.feraxhp.billmate.logic_database.database.entities.Events
import com.feraxhp.billmate.logic_database.database.entities.Funds
import com.feraxhp.billmate.logic_database.database.entities.Transfers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppController(context: Context) {
    val user = User(context)
    private var billMateDatabase =
        Room.databaseBuilder(context, MyDataBase::class.java, "billmateDB").build()
    private var funds = mutableListOf<Funds>()
    private var normalFunds = mutableListOf<Funds>()
    private var savesFunds = mutableListOf<Funds>()
    private var loansFunds = mutableListOf<Funds>()
    private var categories = mutableListOf<Categories>()
    private var transfers = mutableListOf<Transfers>()
    private var events = mutableListOf<Events>()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    init {
        coroutineScope.launch {
            actualize()
            if (normalFunds.isEmpty() && !user.isDeleted() && user.getName() != null) {
                normalFunds.add(
                    Funds(
                        accountName = "Default",
                        amount = 0.0,
                        description = "This is how it will show up"
                    )
                )
                billMateDatabase.FundsDao().insertFund(normalFunds[0])
            }
        }
    }

    private suspend fun actualize() {
        actualizeFunds()
        actualizeCategories()
        actualizeTransfers()
        actualizeEvents()
    }

    // Actualizations
    private suspend fun actualizeFunds() {
        funds.clear()
        normalFunds.clear()
        savesFunds.clear()
        loansFunds.clear()
        normalFunds = billMateDatabase.FundsDao().getFundsByType(0) as MutableList<Funds>
        savesFunds = billMateDatabase.FundsDao().getFundsByType(1) as MutableList<Funds>
        loansFunds = billMateDatabase.FundsDao().getFundsByType(3) as MutableList<Funds>
        funds = (normalFunds + savesFunds + loansFunds) as MutableList<Funds>
    }

    private suspend fun actualizeCategories() {
        categories.clear()
        categories = billMateDatabase.CategoriesDao().getAllCategories() as MutableList<Categories>
    }

    private suspend fun actualizeTransfers() {
        transfers.clear()
        transfers = billMateDatabase.TransfersDao().getAllTransfers() as MutableList<Transfers>
    }

    private suspend fun actualizeEvents() {
        events.clear()
        events = billMateDatabase.EventsDao().getAllEvents() as MutableList<Events>
    }

    // Removals
    fun removeFund(fund: Funds) {
        coroutineScope.launch {
            val currentEvents = billMateDatabase.EventsDao().getEventsByFund(fund.id)
            currentEvents.forEach {
                val category = billMateDatabase.CategoriesDao().getCategoryById(it.category_id)
                category.amount =
                    if (it.type) category.amount - it.amount else category.amount + it.amount
                billMateDatabase.CategoriesDao().updateCategory(category)
            }
            billMateDatabase.EventsDao().removeFundEvents(fund.id)
            billMateDatabase.FundsDao().removeFund(fund.id)
            billMateDatabase.EventsDao().removeEvent(fund.id)
            actualize()
            user.setDeleted(true)
        }
    }

    fun removeCategory(category: Categories) {
        coroutineScope.launch {
            val currentEvents = billMateDatabase.EventsDao().getEventsByCategory(category.id)
            currentEvents.forEach {
                val fund = billMateDatabase.FundsDao().getFundById(it.fund_id)
                fund.amount = if (it.type) fund.amount - it.amount else fund.amount + it.amount
                billMateDatabase.FundsDao().updateFund(fund)
            }
            billMateDatabase.EventsDao().removeCategoryEvents(category.id)
            billMateDatabase.CategoriesDao().removeCategory(category.id)
            billMateDatabase.EventsDao().removeEvent(category.id)
            actualize()
        }
    }

    fun removeTransfer(transfer: Transfers) {
        coroutineScope.launch {
            billMateDatabase.TransfersDao().removeTransfer(transfer.id)
            actualizeTransfers()
        }
    }

    fun removeEvent(event: Events) {
        coroutineScope.launch {
            val currentCategorie =
                billMateDatabase.CategoriesDao().getCategoryById(event.category_id)
            val currentFund = billMateDatabase.FundsDao().getFundById(event.fund_id)

            currentCategorie.amount =
                if (event.type) currentCategorie.amount - event.amount else currentCategorie.amount + event.amount
            currentFund.amount =
                if (event.type) currentFund.amount - event.amount else currentFund.amount + event.amount

            billMateDatabase.CategoriesDao().updateCategory(currentCategorie)
            billMateDatabase.FundsDao().updateFund(currentFund)
            billMateDatabase.EventsDao().removeEvent(event.id)
            actualize()
        }
    }

    // Getters
    fun getAllFunds(): List<Funds> {
        return normalFunds
    }

    fun getAllFundsOnString(): List<String> {
        return if (normalFunds.isNotEmpty()) {
            normalFunds.map { "${it.accountName}: ${it.amount}" }
        } else {
            listOf("")
        }
    }

    fun getTotalBalance(): Double {
        return normalFunds.sumOf { it.amount }
    }

    fun getTotalExpenses(): Double {
        return events.sumOf { if (!it.type) it.amount else 0.0 }
    }

    fun getTotalIncomes(): Double {
        return events.sumOf { if (it.type) it.amount else 0.0 }
    }

    fun getAllCategories(): List<Categories> {
        return categories
    }

    fun getAllCategoriesOnString(): List<String> {
        return if (categories.isNotEmpty()) categories.map { "${it.name}: ${it.amount}" } else {
            listOf("")
        }
    }

    fun getAllEvents(): List<Events> {
        return events
    }

    // Additions
    fun addFund(
        accountName: String,
        titularName: String,
        amount: String,
        description: String,
        type: Int = 0
    ): Boolean {
        var realAmount = amount
        if (accountName == "") return false
        if (realAmount == "") realAmount = "0.0"
        coroutineScope.launch {
            val fund = when (titularName == "") {
                true -> {
                    Funds(
                        accountName = accountName,
                        amount = realAmount.toDouble(),
                        description = description,
                        type = type
                    )
                }

                false -> {
                    Funds(
                        accountName = accountName,
                        amount = realAmount.toDouble(),
                        description = description,
                        titularName = titularName,
                        type = type
                    )
                }
            }
            billMateDatabase.FundsDao().insertFund(fund)
            actualize()
        }
        return true
    }

    fun addCategory(categoryName: String, amount: String, description: String): Boolean {
        var realAmount = amount
        if (categoryName == "") return false
        if (realAmount == "") realAmount = "0.0"
        coroutineScope.launch {
            val category = Categories(
                name = categoryName,
                amount = realAmount.toDouble(),
                icon = 0,
                description = description
            )
            billMateDatabase.CategoriesDao().insertCategory(category)
            actualize()
        }
        return true
    }

    fun addEvent(
        date: Long,
        time: String,
        type: Boolean,
        name: String,
        amount: String,
        description: String,
        fund: Int,
        category: Int
    ): Int {
        val currentFund = this.funds[fund]
        val currentCategory = this.categories[category]

        if (name == "") return 1
        if (amount == "") return 2

        coroutineScope.launch {
            val event = Events(
                date = date,
                time = time,
                type = type,
                name = name,
                amount = amount.toDouble(),
                description = description,
                fund_id = currentFund.id,
                category_id = currentCategory.id
            )
            currentFund.amount =
                if (type) currentFund.amount + amount.toDouble() else currentFund.amount - amount.toDouble()
            currentCategory.amount =
                if (type) currentCategory.amount + amount.toDouble() else currentCategory.amount - amount.toDouble()
            billMateDatabase.EventsDao().insertEvent(event)
            billMateDatabase.FundsDao().updateFund(currentFund)
            billMateDatabase.CategoriesDao().updateCategory(currentCategory)
            actualize()
        }
        return 0
    }


}
