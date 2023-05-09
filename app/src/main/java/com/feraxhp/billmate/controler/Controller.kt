package com.feraxhp.billmate.controler

import android.content.Context
import com.feraxhp.billmate.logic.CategoryController
import com.feraxhp.billmate.logic.FundController
import com.feraxhp.billmate.logic.Keys
import com.feraxhp.billmate.logic.User

class Controller(context: Context) {
    var keys: Keys = Keys(context)
    var user: User = User(context)
    private var category: CategoryController = CategoryController(context)
    private var fundController: FundController = FundController(context)

    fun newCategory() {
        val number = keys.KEYS_CATEGORY_LIST.length + 1
        
    }
}