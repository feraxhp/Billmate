package com.feraxhp.billmate.structure.event

import com.feraxhp.billmate.structure.CashCategory
import com.feraxhp.billmate.structure.Fund

class ExpenseEvent(
    name: String,
    amount: Double,
    category: CashCategory,
    fund: Fund,
    Description: String? = null
) : Event(name, -amount, category, fund, Description) {
    init {
        this.type = false
    }
}