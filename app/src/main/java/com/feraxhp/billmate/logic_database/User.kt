package com.feraxhp.billmate.logic_database

import android.content.Context

class User(
    context: Context,
) {
    private val SHARED_USER = "User"
    private val SHARED_USER_NAME = "User.name"
    private val SHARED_USER_DEF_DELETED = "User.deleted"
    private val store = context.getSharedPreferences(SHARED_USER, Context.MODE_PRIVATE)

    fun setName(name: String?) {
        store.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun getName(): String? {
        return store.getString(SHARED_USER_NAME, null)
    }

    fun setDeleted(deleted: Boolean) {
        store.edit().putBoolean(SHARED_USER_DEF_DELETED, deleted).apply()
    }

    fun isDeleted(): Boolean {
        return store.getBoolean(SHARED_USER_DEF_DELETED, false)
    }
}