package com.feraxhp.billmate.structure

import androidx.compose.ui.graphics.Color
import com.feraxhp.billmate.structure.event.Event

class CashCategory(var name: String, var color: Color, var icon: String) {
    private lateinit var events: ArrayList<Event>
    private var amount: Double = 0.0

    public fun addEvent(event: Event) {
        this.events.add(event)
        this.amount += event.amount
    }

    public fun removeEvent(event: Event) {
        this.events.remove(event)
        this.amount -= event.amount
    }
}