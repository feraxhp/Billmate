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

class Controller(context: Context) {
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
            actualizeFunds()
            actualizeCategories()
            actualizeTransfers()
            actualizeExpenses()
            actualizeIncomes()
            if (funds.isEmpty() && user.isDeleted()) {
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

    private suspend fun actualizeFunds() {
        funds = billMateDatabase.FundsDao().getAllFunds() as MutableList<Funds>
    }

    private suspend fun actualizeCategories() {
        categories = billMateDatabase.CategoriesDao().getAllCategories() as MutableList<Categories>
    }

    private suspend fun actualizeTransfers() {
        transfers = billMateDatabase.TransfersDao().getAllTransfers() as MutableList<Transfers>
    }

    private suspend fun actualizeExpenses() {
        expenses = billMateDatabase.EventsDao().getAllExpenses() as MutableList<Events>
    }

    private suspend fun actualizeIncomes() {
        incomes = billMateDatabase.EventsDao().getAllIncomes() as MutableList<Events>
    }

    fun getAllFunds(): List<Funds> {
        return funds
    }

    fun removeFund(fund: Funds) {
        coroutineScope.launch {
            billMateDatabase.FundsDao().removeFund(fund.id)
            actualizeFunds()
        }
    }

    fun getTotalBalance(): Double {
        return funds.sumOf { it.amount }
    }

    fun getTotalExpenses(): Double {
        return funds.sumOf { it.amount }
    }
}
