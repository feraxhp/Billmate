package com.feraxhp.billmate.logic

import android.content.Context
import com.feraxhp.billmate.logic.event.Event

class Fund(
    context: Context,
    private var amount: Double = 0.0,
    var name: String,
    var description: String? = null/*, var associatedBank:String? = null*/
) {
    val SHARED_NAME = "Fund"
    val storage = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    private lateinit var events: ArrayList<Event>

    public fun addEvent(event: Event) {
        this.events.add(event)
        this.amount += event.amount
    }

    public fun removeEvent(event: Event) {
        this.events.remove(event)
        this.amount -= event.amount
    }

    public fun addMount(amount: Double) {
        this.amount += amount
    }

    public fun getAmount(cashPrefix: String? = null, cashSuffix: String? = null): String {
        return cashPrefix + " " + this.amount.toString() + " " + cashSuffix;
    }

    public fun changeName(name: String) {
        this.name = name
    }

    public fun changeDescription(description: String?) {
        this.description = description
    }
}