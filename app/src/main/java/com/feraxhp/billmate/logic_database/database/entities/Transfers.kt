package com.feraxhp.billmate.logic_database.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transferencias", foreignKeys = [
        ForeignKey(
            entity = Funds::class,
            parentColumns = ["id"],
            childColumns = ["origin_fund_id"]
        ),
        ForeignKey(
            entity = Funds::class,
            parentColumns = ["id"],
            childColumns = ["target_fund_id"]
        )
    ]
)
data class Transfers(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val amount: Double,
    val description: String,
    @ColumnInfo(name = "origin_fund_id")
    val origin_fund_id: Long,
    @ColumnInfo(name = "target_fund_id")
    val target_fund_id: Long
)
