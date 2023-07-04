package com.feraxhp.billmate.controller

import android.content.Context
import androidx.room.Room
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat
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
                this@AppController.addFund(
                    titularName = "",
                    accountName = "Default",
                    amount = "0.0",
                    description = "This is a description"
                )
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
        loansFunds = billMateDatabase.FundsDao().getFundsByType(2) as MutableList<Funds>
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
            val currentOriginTransfers =
                billMateDatabase.TransfersDao().getTransfersByOriginFundId(fund.id)
            val currentTargetTransfers =
                billMateDatabase.TransfersDao().getTransfersByDestinationFundId(fund.id)

            currentOriginTransfers.forEach { transfer ->
                val originFund = billMateDatabase.FundsDao().getFundById(transfer.origin_fund_id)
                originFund.amount = originFund.amount + transfer.amount
                billMateDatabase.FundsDao().updateFund(originFund)
                billMateDatabase.TransfersDao().removeTransfer(transfer.id)
            }
            currentTargetTransfers.forEach { transfer ->
                val targetFund = billMateDatabase.FundsDao().getFundById(transfer.target_fund_id)
                targetFund.amount = targetFund.amount - transfer.amount
                billMateDatabase.FundsDao().updateFund(targetFund)
                billMateDatabase.TransfersDao().removeTransfer(transfer.id)
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
            val originFund = billMateDatabase.FundsDao().getFundById(transfer.origin_fund_id)
            val targetFund = billMateDatabase.FundsDao().getFundById(transfer.target_fund_id)
            originFund.amount = originFund.amount + transfer.amount
            targetFund.amount = targetFund.amount - transfer.amount
            billMateDatabase.FundsDao().updateFund(originFund)
            billMateDatabase.FundsDao().updateFund(targetFund)
            billMateDatabase.TransfersDao().removeTransfer(transfer.id)
            actualize()
        }
    }

    fun removeEvent(event: Events) {
        coroutineScope.launch {
            val currentCategory =
                billMateDatabase.CategoriesDao().getCategoryById(event.category_id)
            val currentFund = billMateDatabase.FundsDao().getFundById(event.fund_id)

            currentCategory.amount =
                if (event.type) currentCategory.amount - event.amount else currentCategory.amount + event.amount
            currentFund.amount =
                if (event.type) currentFund.amount - event.amount else currentFund.amount + event.amount

            billMateDatabase.CategoriesDao().updateCategory(currentCategory)
            billMateDatabase.FundsDao().updateFund(currentFund)
            billMateDatabase.EventsDao().removeEvent(event.id)
            actualize()
        }
    }

    // Getters
    fun getAllFunds(): List<Funds> {
        return funds
    }

    fun getFundByID(originFundId: Long): Funds? {
        return funds.find { it.id == originFundId }
    }

    fun getCategoryByID(categoryId: Long): Categories? {
        return categories.find { it.id == categoryId }
    }

    fun getAllFundsOnString(): List<String> {
        return if (funds.isNotEmpty()) {
            funds.map { "${it.accountName}: ${it.amount.toMoneyFormat()}" }
        } else {
            listOf("")
        }
    }

//    fun getAllNormalFundsOnString(): List<String> {
//        return if (normalFunds.isNotEmpty()) {
//            normalFunds.map { "${it.accountName}: ${it.amount.toMoneyFormat()}" }
//        } else {
//            listOf("")
//        }
//    }

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
        return if (categories.isNotEmpty()) categories.map { "${it.name}: ${it.amount.toMoneyFormat()}" } else {
            listOf("")
        }
    }

    fun getAllEvents(): List<Events> {
        return events
    }

    fun getAllTransfers(): List<Transfers> {
        return transfers
    }

    // Additions
    fun addFund(
        accountName: String,
        titularName: String,
        amount: String,
        description: String,
        type: Int = 0
    ): Int {
        var realAmount = amount
        if (accountName == "") return 1
        if (realAmount == "") realAmount = "0.0"
        try {
            realAmount.toDouble()
        } catch (e: Exception) {
            return 2
        }
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
        return 0
    }

    fun addCategory(categoryName: String, amount: String, description: String): Int {
        var realAmount = amount

        if (categoryName == "") return 1
        if (realAmount == "") realAmount = "0.0"
        try {
            realAmount.toDouble()
        } catch (e: Exception) {
            return 2
        }
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
        return 0
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

        try {
            amount.toDouble()
        } catch (e: Exception) {
            return 2
        }

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

    fun addTransfer(
        originFund: Int,
        targetFund: Int,
        amount: String,
        date: Long,
        time: String,
        description: String
    ): Int {
        val currentOriginFund = this.funds[originFund]
        val currentTargetFund = this.funds[targetFund]

        if (amount == "") return 1

        coroutineScope.launch {
            val transfer = Transfers(
                origin_fund_id = currentOriginFund.id,
                target_fund_id = currentTargetFund.id,
                amount = amount.toDouble(),
                date = date,
                time = time,
                description = description
            )
            currentOriginFund.amount = currentOriginFund.amount - amount.toDouble()
            currentTargetFund.amount = currentTargetFund.amount + amount.toDouble()
            billMateDatabase.FundsDao().updateFund(currentOriginFund)
            billMateDatabase.FundsDao().updateFund(currentTargetFund)
            billMateDatabase.TransfersDao().insertTransfer(transfer)
            actualize()
        }
        return 0
    }

    // Updates
    fun changeFundsDefaultTitularName(oldTitularName: String, newTitularName: String) {
        coroutineScope.launch {
            val funds = billMateDatabase.FundsDao().getFundsByTitularName(oldTitularName)
            funds.forEach {
                billMateDatabase.FundsDao().updateFund(it.copy(titularName = newTitularName))
            }
            actualize()
        }
    }

    fun editEvent(editedEvent: Events): Boolean {
        val actualEvent = viewController.event2Edit ?: return false
        coroutineScope.launch {
            val actualFund = getFundByID(actualEvent.fund_id)
            val actualCategory = getCategoryByID(actualEvent.category_id)

            val targetFund = this@AppController.getFundByID(editedEvent.fund_id)
            val targetCategory = this@AppController.getCategoryByID(editedEvent.category_id)

            if (targetFund != actualFund || actualEvent.amount != editedEvent.amount){
                billMateDatabase
                    .FundsDao()
                    .updateFund(
                        actualFund!!
                            .copy(
                                amount = if (actualEvent.type) actualFund.amount - actualEvent.amount
                                else actualFund.amount + actualEvent.amount
                            )
                    )
                billMateDatabase
                    .FundsDao()
                    .updateFund(
                        targetFund!!.copy(
                            amount = if (editedEvent.type) targetFund.amount + editedEvent.amount
                            else targetFund.amount - editedEvent.amount
                        )
                    )
            }

            if (targetCategory != actualCategory || actualEvent.amount != editedEvent.amount){
                billMateDatabase
                    .CategoriesDao()
                    .updateCategory(
                        actualCategory!!
                            .copy(
                                amount = if (actualEvent.type) actualCategory.amount - actualEvent.amount
                                else actualCategory.amount + actualEvent.amount
                            )
                    )

                billMateDatabase
                    .CategoriesDao()
                    .updateCategory(
                        targetCategory!!.copy(
                            amount = if (editedEvent.type) targetCategory.amount + editedEvent.amount
                            else targetCategory.amount - editedEvent.amount
                        )
                    )
            }

            billMateDatabase.EventsDao().updateEvent(editedEvent)
            viewController.event2Edit = null
            actualize()
        }
        return true
    }

    fun editTransfer(editedTransfer: Transfers): Boolean {
        val actualTransfer = viewController.transfer2Edit ?: return false
        coroutineScope.launch {
            val actualOriginFund = getFundByID(actualTransfer.origin_fund_id)
            val actualTargetFund = getFundByID(actualTransfer.target_fund_id)

            val destinationOriginFund = getFundByID(editedTransfer.origin_fund_id)
            val destinationTargetFund = getFundByID(editedTransfer.target_fund_id)

            if (destinationOriginFund != actualOriginFund || actualTransfer.amount != editedTransfer.amount){
                actualOriginFund!!.amount = actualOriginFund.amount + actualTransfer.amount
                destinationOriginFund!!.amount = destinationOriginFund.amount - editedTransfer.amount
                billMateDatabase.FundsDao().updateFund(actualOriginFund)
                billMateDatabase.FundsDao().updateFund(destinationOriginFund)
            }
            if (destinationTargetFund != actualTargetFund || actualTransfer.amount != editedTransfer.amount){
               actualTargetFund!!.amount = actualTargetFund.amount - actualTransfer.amount
               destinationTargetFund!!.amount = destinationTargetFund.amount + editedTransfer.amount
               billMateDatabase.FundsDao().updateFund(actualTargetFund)
               billMateDatabase.FundsDao().updateFund(destinationTargetFund)
            }
            billMateDatabase.TransfersDao().updateTransfer(editedTransfer)
            viewController.transfer2Edit = null
            actualize()
        }
        return true
    }
}
