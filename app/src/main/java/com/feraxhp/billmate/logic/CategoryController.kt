package com.feraxhp.billmate.logic

import android.content.Context

class CategoryController(
    val context: Context
) {
    val store = context.getSharedPreferences("Default", Context.MODE_PRIVATE)

    fun newCategory(
        name: String,
        amount: Float,
        icon: Int,
        key: String = "Default",
        event: String? = null
    ) {
        this.store.edit().putString(key + ".name", name).apply()
        this.store.edit().putFloat(key + ".amount", amount).apply()
        this.store.edit().putInt(key + ".icon", icon).apply()
        event?.let {
            this.addEvent(key, it)
        }
    }


    // Amount
    fun setAmount(amount: Float, key: String = "Default") {
        this.store.edit().putFloat(key + ".amount", amount).apply()
    }

    fun getAmount(key: String = "Default"): Float {
        return store.getFloat(key + ".amount", 0f)
    }

    // Name
    fun setName(name: String?, key: String = "Default") {
        this.store.edit().putString(key + ".name", name).apply()
    }

    fun getName(key: String = "Default"): String? {
        return store.getString(key + ".name", null)
    }

    // Icon
    fun setIcon(icon: Int, key: String = "Default") {
        this.store.edit().putInt(key + ".icon", icon).apply()
    }

    fun getIcon(key: String = "Default"): Int {
        return store.getInt(key + ".icon", 0)
    }

    // Event
    fun addEvent(key: String = "Default", event: String) {
        val events = this.store.getStringSet(key, null)?.toList() as MutableList<String>
        events.add(event)
        this.store.edit().putStringSet(key + ".event", events.toSet()).apply()
    }

    fun getEvents(key: String = "Default"): List<String>? {
        return store.getStringSet(key + ".event", null)?.toList()
    }
}