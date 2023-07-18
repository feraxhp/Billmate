package com.feraxhp.billmate.logic_database.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Categories", indices = [Index(value = ["name"], unique = false)])
data class Categories(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "father", defaultValue = "0")
    val father: Long = 0L,
    val name: String,
    val icon: Int,
    var amount: Double,
    val description: String
)
