package com.feraxhp.billmate.logic

import android.content.Context


class CategoryController(
    val context: Context
) {
    fun newCategory(
        key: String,
        name: String,
        amount: Float = 0f,
        icon: Int = 0,
        event: String? = null
    ) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putString(key + ".name", name).apply()
        store.edit().putFloat(key + ".amount", amount).apply()
        store.edit().putInt(key + ".icon", icon).apply()
        event?.let {
            this.addEvent(key, it)
        }
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

    // Name
    fun setName(name: String?, key: String = "Default") {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)

        store.edit().putString(key + ".name", name).apply()
    }

    fun getName(key: String = "Default"): String? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)

        return store.getString(key + ".name", null)
    }

    // Icon
    fun setIcon(icon: Int, key: String = "Default") {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)

        store.edit().putInt(key + ".icon", icon).apply()
    }

    fun getIcon(key: String = "Default"): Int {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)

        return store.getInt(key + ".icon", 0)
    }

    // Event
    fun addEvent(key: String = "Default", event: String) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        val events = store.getStringSet(key, null)?.toList() as MutableList<String>
        events.add(event)
        store.edit().putStringSet(key + ".event", events.toSet()).apply()
    }

    fun getEvents(key: String = "Default"): List<String>? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)

        return store.getStringSet(key + ".event", null)?.toList()
    }
}