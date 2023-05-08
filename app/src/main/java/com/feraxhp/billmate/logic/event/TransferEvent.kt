package com.feraxhp.billmate.logic.event

import android.content.Context

class TransferEvent(
    context: Context
) {
    var store = context.getSharedPreferences("Default", Context.MODE_PRIVATE)

    fun newTransferEvent(
        key: String,
    ) {

    }
}