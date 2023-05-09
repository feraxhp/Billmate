package com.feraxhp.billmate.logic.event

import android.content.Context

class Event(
    val context: Context
) {
    fun newEvent(
        key: String,
        name: String,
        amount: Float,
        category: String,
        fund: String,
        Description: String? = null
    ) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putString(key + ".name", name).apply()
        store.edit().putFloat(key + ".amount", amount).apply()
        store.edit().putString(key + ".category", category).apply()
        store.edit().putString(key + ".fund", fund).apply()
        store.edit().putString(key + ".description", Description).apply()
    }


    // Name
    fun setName(key: String, name: String) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putString(key + ".name", name).apply()
    }

    fun getName(key: String): String? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getString(key + ".name", null)
    }

    // Amount
    fun setAmount(key: String, amount: Float) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putFloat(key + ".amount", amount).apply()
    }

    fun getAmount(key: String): Float {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getFloat(key + ".amount", 0f)
    }

    // Fund
    fun setFund(key: String, fund: String) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putString(key + ".fund", fund).apply()
    }

    fun getFund(key: String): String? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getString(key + ".fund", null)
    }

    // Category
    fun setCategory(key: String, category: String) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putString(key + ".category", category).apply()
    }

    fun getCategory(key: String): String? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getString(key + ".category", null)
    }

    // Description
    fun setDescription(key: String, description: String?) {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        store.edit().putString(key + ".description", description).apply()
    }

    fun getDescription(key: String): String? {
        val store = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return store.getString(key + ".description", null)
    }
}