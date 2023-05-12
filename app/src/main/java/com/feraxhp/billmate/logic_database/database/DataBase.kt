package com.feraxhp.billmate.logic_database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.feraxhp.billmate.logic_database.database.entities.Categories
import com.feraxhp.billmate.logic_database.database.entities.Events
import com.feraxhp.billmate.logic_database.database.entities.Funds
import com.feraxhp.billmate.logic_database.database.entities.Transfers
import com.feraxhp.billmate.logic_database.database.repositories.CategoriesDao
import com.feraxhp.billmate.logic_database.database.repositories.EventsDao
import com.feraxhp.billmate.logic_database.database.repositories.FundsDao
import com.feraxhp.billmate.logic_database.database.repositories.TransfersDao

@Database(
    entities = [
        Funds::class,
        Categories::class,
        Events::class,
        Transfers::class],
    version = 1
)
abstract class DataBas : RoomDatabase() {
    abstract fun FundsDao(): FundsDao
    abstract fun CategoriesDao(): CategoriesDao
    abstract fun EventsDao(): EventsDao
    abstract fun TransfersDao(): TransfersDao
}
