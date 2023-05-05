package com.feraxhp.billmate.logic

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.feraxhp.billmate.logic.event.Event

class CashCategory(
    val context: Context,
    var name: String, var color: Color, var icon: String
) {
    private lateinit var events: ArrayList<Event>
    private var amount: Double = 0.0

    val SHARED_NAME = "CashCategory"
    fun addEvent(event: Event) {
        this.events.add(event)
        this.amount += event.amount
    }

    fun removeEvent(event: Event) {
        this.events.remove(event)
        this.amount -= event.amount
    }
}