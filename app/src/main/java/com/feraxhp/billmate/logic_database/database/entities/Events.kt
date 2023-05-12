package com.feraxhp.billmate.logic_database.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Events", foreignKeys = [
        ForeignKey(
            entity = Funds::class,
            parentColumns = ["id"],
            childColumns = ["fund_id"]
        ),
        ForeignKey(
            entity = Categories::class,
            parentColumns = ["id"],
            childColumns = ["category_id"]
        )
    ]
)
data class Events(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val amount: Double,
    val description: String,
    @ColumnInfo(name = "fund_id")
    val fund_id: Long,
    @ColumnInfo(name = "category_id")
    val category_id: Long
)