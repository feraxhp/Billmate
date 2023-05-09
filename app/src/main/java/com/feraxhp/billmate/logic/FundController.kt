package com.feraxhp.billmate.logic

import android.content.Context

class FundController(
    val context: Context
) {
    fun newFund(
        name: String,
        amount: Float,
        description: String,
        key: String = "Default",
        event: String? = null
    ) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putString(key + ".name", name).apply()
        store.edit().putFloat(key + ".amount", amount).apply()
        store.edit().putString(key + ".description", description).apply()
        event?.let {
            this.addEvent(key, it)
        }
    }

    // Name
    fun setName(name: String, key: String = "Default") {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putString(key + ".name", name).apply()
    }

    fun getName(key: String = "Default"): String? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getString(key + ".name", null)
    }

    // Amount
    fun setAmount(amount: Float, key: String = "Default") {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putFloat(key + ".amount", amount).apply()
    }

    fun getAmount(key: String = "Default"): Float {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getFloat(key + ".amount", 0f)
    }

    // Description
    fun setDescription(description: String?, key: String = "Default") {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putString(key + ".description", description).apply()
    }

    fun getDescription(key: String = "Default"): String? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getString(key + ".description", null)
    }

    // Event
    fun addEvent(key: String = "Default", event: String) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        val events = store.getStringSet(key + ".event", null)?.toList() as MutableList<String>
        events.add(event)
        store.edit().putStringSet(key + ".event", events.toSet()).apply()
    }

    fun getEvents(key: String = "Default"): List<String>? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getStringSet(key + ".event", null)?.toList()
    }

    // Event transfer
    fun addTransferEvent(key: String = "Default", event: String) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        val events =
            store.getStringSet(key + ".transferEvent", null)?.toList() as MutableList<String>
        events.add(event)
        store.edit().putStringSet(key + ".transferEvent", events.toSet()).apply()
    }

    fun getTransferEvents(key: String = "Default"): List<String>? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getStringSet(key + ".transferEvent", null)?.toList()
    }
}