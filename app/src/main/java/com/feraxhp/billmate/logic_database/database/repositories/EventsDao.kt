package com.feraxhp.billmate.logic_database.database.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.feraxhp.billmate.logic_database.database.entities.Events

@Dao
interface EventsDao {
    @Query("SELECT * FROM Events")
    suspend fun getAllEvents(): List<Events>

    @Query("DELETE FROM Events WHERE fund_id = :id")
    suspend fun removeFundEvents(id: Long)

    @Query("DELETE FROM Events WHERE category_id = :id")
    suspend fun removeCategoryEvents(id: Long)

    @Query("SELECT * FROM Events WHERE category_id = :id")
    suspend fun getEventsByCategory(id: Long): List<Events>

    @Query("SELECT * FROM Events WHERE fund_id = :id")
    suspend fun getEventsByFund(id: Long): List<Events>
    
    @Query("SELECT * FROM Events WHERE id = :id")
    suspend fun getEventById(id: Long): Events

    @Insert
    suspend fun insertEvent(Event: Events)

    @Insert
    suspend fun insertEvents(Events: List<Events>)

    @Update
    suspend fun updateEvent(Event: Events)

    @Query("DELETE FROM Events WHERE id = :id")
    suspend fun removeEvent(id: Long)
}
