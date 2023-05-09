package com.feraxhp.billmate.logic.event

import com.feraxhp.billmate.logic.CashCategory
import com.feraxhp.billmate.logic.Fund

open class Event(
    var name: String,
    var amount: Double,
    var category: CashCategory,
    var fund: Fund,
    var Description: String? = null
) {
    protected var type: Boolean? = null

    init {
        this.fund.addEvent(this)
        this.category.addEvent(this)
    }

    public fun changeName(name: String) {
        this.modificacion { this.name = name }
    }

    public open fun changeAmount(amount: Double) {
        this.modificacion {
            if (this.type!!) this.amount += amount
            else this.amount -= amount
        }
    }

    public fun changeCategory(category: CashCategory) {
        this.modificacion { this.category = category }
    }

    public fun changeFund(fund: Fund) {
        this.modificacion { this.fund = fund }
    }

    public fun changeDescription(Description: String? = null) {
        this.modificacion { this.Description = Description }
    }

    private fun modificacion(funcion: () -> Unit) {
        this.fund.removeEvent(this)
        this.category.removeEvent(this)
        funcion()
        this.fund.addEvent(this)
        this.category.addEvent(this)
    }

}