package com.feraxhp.billmate.controller

import android.content.Context
import androidx.room.Room
import com.feraxhp.billmate.logic_database.User
import com.feraxhp.billmate.logic_database.database.MyDataBase
import com.feraxhp.billmate.logic_database.database.entities.Funds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Controller(context: Context) {
    val user = User(context)
    var billMateDatabase =
        Room.databaseBuilder(context, MyDataBase::class.java, "billmateDB").build()
    private var funds = mutableListOf<Funds>()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    init {
        coroutineScope.launch {
            actualizeFunds()
            if (funds.isEmpty()) {
                funds.add(Funds(name = "default", amount = 0.0, description = ""))
                billMateDatabase.FundsDao().insertFund(funds[0])
            }
        }
    }

    private suspend fun actualizeFunds() {
        funds = billMateDatabase.FundsDao().getAllFunds() as MutableList<Funds>
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
}
