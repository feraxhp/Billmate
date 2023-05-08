package com.feraxhp.billmate.logic.event

import android.content.Context

class Event(
    context: Context
) {
//    protected var type: Boolean? = null

    var store = context.getSharedPreferences("Default", Context.MODE_PRIVATE)

    fun newEvent(
        key: String,
        name: String,
        amount: Float,
        category: String,
        fund: String,
        Description: String? = null
    ) {
        this.store.edit().putString(key + ".name", name).apply()
        this.store.edit().putFloat(key + ".amount", amount).apply()
        this.store.edit().putString(key + ".category", category).apply()
        this.store.edit().putString(key + ".fund", fund).apply()
        this.store.edit().putString(key + ".description", Description).apply()
    }


    // Name
    fun setName(key: String, name: String) {
        this.store.edit().putString(key + ".name", name).apply()
    }

    fun getName(key: String): String? {
        return store.getString(key + ".name", null)
    }

    // Amount
    fun setAmount(key: String, amount: Float) {
        this.store.edit().putFloat(key + ".amount", amount).apply()
    }

    fun getAmount(key: String): Float {
        return store.getFloat(key + ".amount", 0f)
    }

    // Fund
    fun setFund(key: String, fund: String) {
        this.store.edit().putString(key + ".fund", fund).apply()
    }

    fun getFund(key: String): String? {
        return store.getString(key + ".fund", null)
    }

    // Category
    fun setCategory(key: String, category: String) {
        this.store.edit().putString(key + ".category", category).apply()
    }

    fun getCategory(key: String): String? {
        return store.getString(key + ".category", null)
    }

    // Description
    fun setDescription(key: String, description: String?) {
        this.store.edit().putString(key + ".description", description).apply()
    }

    fun getDescription(key: String): String? {
        return store.getString(key + ".description", null)
    }
}