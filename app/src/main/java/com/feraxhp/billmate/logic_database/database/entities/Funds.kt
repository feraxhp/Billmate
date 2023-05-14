package com.feraxhp.billmate.logic_database.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController

@Entity(tableName = "Funds", indices = [Index(value = ["accountName"], unique = true)])
data class Funds(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val accountName: String,
    val titularName: String = appController.user.getName()!!,
    val amount: Double,
    val description: String
)
