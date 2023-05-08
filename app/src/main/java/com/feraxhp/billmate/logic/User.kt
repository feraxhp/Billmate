package com.feraxhp.billmate.logic

import android.content.Context

class User(
    context: Context,
) {
    val SHARED_USER = "User"
    val SHARED_USER_NAME = "User.name"
    private val store = context.getSharedPreferences(SHARED_USER, Context.MODE_PRIVATE)

    fun setName(name: String?) {
        store.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun getName(): String? {
        return store.getString(SHARED_USER_NAME, null)
    }
}