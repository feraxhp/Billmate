package com.feraxhp.billmate.logic

import android.content.Context

class Keys(context: Context) {

    val KEYS_FUND = "KEYS_FUND"
    val KEYS_CATEGORY = "KEYS_CATEGORY"

    val KEYS_FUND_LIST = "KEYSFUNDLIST"
    val KEYS_CATEGORY_LIST = "KEYSCATEGORYLIST"

    var keysFundList = mutableListOf<String>("Default")
    var keysCategoryList = mutableListOf<String>("Default")

    private val keysFundStore = context.getSharedPreferences(KEYS_FUND, Context.MODE_PRIVATE)
    private val keysCategoryStore =
        context.getSharedPreferences(KEYS_CATEGORY, Context.MODE_PRIVATE)

    init {
        keysFundList =
            keysFundStore.getStringSet(KEYS_FUND_LIST, null)?.toList() as MutableList<String>
        keysCategoryList = keysCategoryStore.getStringSet(KEYS_CATEGORY_LIST, null)
            ?.toList() as MutableList<String>
    }

    fun addFund(name: String) {
        keysFundList.add(name)
        keysFundStore.edit().putStringSet(KEYS_FUND_LIST, keysFundList.toSet()).apply()
    }

    fun addCategory(name: String) {
        keysCategoryList.add(name)
        keysCategoryStore.edit().putStringSet(KEYS_CATEGORY_LIST, keysCategoryList.toSet()).apply()
    }
}
