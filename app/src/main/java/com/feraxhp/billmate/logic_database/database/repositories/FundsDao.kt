package com.feraxhp.billmate.logic_database.database.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.feraxhp.billmate.logic_database.database.entities.Funds

@Dao
interface FundsDao {

    @Query("SELECT * FROM funds")
    suspend fun getAllFunds(): List<Funds>

    @Query("SELECT * FROM funds WHERE id = :id")
    suspend fun getFundById(id: Long): Funds

    @Insert
    suspend fun insertFund(fund: Funds)

    @Update
    suspend fun updateFund(fund: Funds)
}
