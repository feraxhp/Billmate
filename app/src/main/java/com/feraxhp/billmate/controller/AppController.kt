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
    var billMateDatabase =
        Room.databaseBuilder(context, MyDataBase::class.java, "billmateDB").build()
    private var funds = mutableListOf<Funds>()
    private var categories = mutableListOf<Categories>()
    private var transfers = mutableListOf<Transfers>()
    private var expenses = mutableListOf<Events>()
    private var incomes = mutableListOf<Events>()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    init {
        coroutineScope.launch {
            actualize()
            if (funds.isEmpty() && !user.isDeleted()) {
                funds.add(
                    Funds(
                        accountName = "Default",
                        amount = 0.0,
                        description = "This is how it will show up"
                    )
                )
                billMateDatabase.FundsDao().insertFund(funds[0])
            }
        }
    }

    private suspend fun actualize() {
        actualizeFunds()
        actualizeCategories()
        actualizeTransfers()
        actualizeExpenses()
        actualizeIncomes()
    }

    // Actualizations
    private suspend fun actualizeFunds() {
        funds.clear()
        funds = billMateDatabase.FundsDao().getAllFunds() as MutableList<Funds>
    }

    private suspend fun actualizeCategories() {
        categories.clear()
        categories = billMateDatabase.CategoriesDao().getAllCategories() as MutableList<Categories>
    }

    private suspend fun actualizeTransfers() {
        transfers.clear()
        transfers = billMateDatabase.TransfersDao().getAllTransfers() as MutableList<Transfers>
    }

    private suspend fun actualizeExpenses() {
        expenses.clear()
        expenses = billMateDatabase.EventsDao().getAllExpenses() as MutableList<Events>
    }

    private suspend fun actualizeIncomes() {
        incomes.clear()
        incomes = billMateDatabase.EventsDao().getAllIncomes() as MutableList<Events>
    }

    // Removals
    fun removeFund(fund: Funds) {
        coroutineScope.launch {
            billMateDatabase.FundsDao().removeFund(fund.id)
            billMateDatabase.EventsDao().removeEvent(fund.id)
            actualize()
        }
    }

    fun removeCategory(category: Categories) {
        coroutineScope.launch {
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

    fun removeExpense(expense: Events) {
        coroutineScope.launch {
            billMateDatabase.EventsDao().removeEvent(expense.id)
            actualizeExpenses()
        }
    }

    fun removeIncome(income: Events) {
        coroutineScope.launch {
            billMateDatabase.EventsDao().removeEvent(income.id)
            actualizeIncomes()
        }
    }

    // Getters
    fun getAllFunds(): List<Funds> {
        return funds
    }

    fun getTotalBalance(): Double {
        return funds.sumOf { it.amount }
    }

    fun getTotalExpenses(): Double {
        return expenses.sumOf { it.amount }
    }

    fun getTotalIncomes(): Double {
        return incomes.sumOf { it.amount }
    }

    fun getAllCategories(): List<Categories> {
        return categories
    }

    fun getAllIncomes(): List<Events> {
        return incomes
    }

    fun getAllExpenses(): List<Events> {
        return expenses
    }

    // Additions
    fun addFund(
        accountName: String,
        titularName: String,
        amount: String,
        description: String
    ): Boolean {
        var realAmount = amount
        if (accountName == "") return false
        if (realAmount == "") realAmount = "0.0"
        coroutineScope.launch {
            val fund = when (titularName.equals("")) {
                true -> {
                    Funds(
                        accountName = accountName,
                        amount = realAmount.toDouble(),
                        description = description
                    )
                }

                false -> {
                    Funds(
                        accountName = accountName,
                        amount = realAmount.toDouble(),
                        description = description,
                        titularName = titularName
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
}
