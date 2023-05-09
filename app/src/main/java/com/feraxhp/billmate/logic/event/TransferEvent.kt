package com.feraxhp.billmate.logic.event

import android.content.Context

class TransferEvent(
    context: Context
) {
    var store = context.getSharedPreferences("Default", Context.MODE_PRIVATE)

    fun newTransferEvent(
        key: String,
        name: String,
        amount: Float,
        originFund: String,
        destinationFund: String
    ) {
        store.edit().putString(key + ".name", name).apply()
        store.edit().putFloat(key + ".amount", amount).apply()
        store.edit().putString(key + ".originFund", originFund).apply()
        store.edit().putString(key + ".destinationFund", destinationFund).apply()
    }

    fun setName(key: String, name: String) {
        store.edit().putString(key + ".name", name).apply()
    }

    fun getName(key: String): String? {
        return store.getString(key + ".name", null)
    }

    fun setAmount(key: String, amount: Float) {
        store.edit().putFloat(key + ".amount", amount).apply()
    }

    fun getAmount(key: String): Float {
        return store.getFloat(key + ".amount", 0f)
    }

    fun setOriginFund(key: String, originFund: String) {
        store.edit().putString(key + ".originFund", originFund).apply()
    }

    fun getOriginFund(key: String): String? {
        return store.getString(key + ".originFund", null)
    }

    fun setDestinationFund(key: String, destinationFund: String) {
        store.edit().putString(key + ".destinationFund", destinationFund).apply()
    }

    fun getDestinationFund(key: String): String? {
        return store.getString(key + ".destinationFund", null)
    }

}