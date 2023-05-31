package com.feraxhp.billmate.logic_database.database.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.feraxhp.billmate.logic_database.database.entities.Transfers

@Dao
interface TransfersDao {
    @Query("SELECT * FROM Transfers")
    suspend fun getAllTransfers(): List<Transfers>

    @Query("SELECT * FROM Transfers WHERE id = :id")
    suspend fun getTransferById(id: Long): Transfers

    @Query("SELECT * FROM Transfers WHERE origin_fund_id = :id")
    suspend fun getTransfersByOriginFundId(id: Long): List<Transfers>

    @Query("SELECT * FROM Transfers WHERE target_fund_id = :id")
    suspend fun getTransfersByDestinationFundId(id: Long): List<Transfers>

    @Insert
    suspend fun insertTransfer(Transfer: Transfers)

    @Insert
    suspend fun insertTransfers(Transfers: List<Transfers>)

    @Update
    suspend fun updateTransfer(Transfer: Transfers)

    @Query("DELETE FROM Transfers WHERE id = :id")
    suspend fun removeTransfer(id: Long)
}
