package com.feraxhp.billmate.logic_database.database.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.feraxhp.billmate.logic_database.database.entities.Funds

@Dao
interface FundsDao {
    //    @ColumnInfo
    @Query("SELECT * FROM Funds")
    suspend fun getAllFunds(): List<Funds>

    @Query("SELECT * FROM Funds WHERE id = :id")
    suspend fun getFundById(id: Long): Funds

    @Insert
    suspend fun insertFund(fund: Funds)

    @Insert
    suspend fun insertFunds(funds: List<Funds>)

    @Update
    suspend fun updateFund(fund: Funds)

    @Query("DELETE FROM Funds WHERE id = :id")
    suspend fun removeFund(id: Long)
}
