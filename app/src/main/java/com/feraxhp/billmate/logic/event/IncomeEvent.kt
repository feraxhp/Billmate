package com.feraxhp.billmate.logic.event

import com.feraxhp.billmate.logic.CashCategory
import com.feraxhp.billmate.logic.Fund

class IncomeEvent(
    name: String,
    amount: Double,
    category: CashCategory,
    fund: Fund,
    Description: String? = null
) : Event(name, amount, category, fund, Description) {
    init {
        this.type = true
    }
}