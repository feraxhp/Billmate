package com.feraxhp.billmate.controller

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.feraxhp.billmate.activitys.CreateNewCategory
import com.feraxhp.billmate.activitys.CreateNewEvents
import com.feraxhp.billmate.activitys.CreateNewFund
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.logic_database.database.entities.Events
import com.feraxhp.billmate.logic_database.database.entities.Transfers

class ViewController(private val context: Context) {
    fun startMainActivity() {
        ContextCompat.startActivity(
            context, Intent(context, MainActivity::class.java),
            null
        )
    }

    fun startCreateNewFund() {
        ContextCompat.startActivity(
            context, Intent(context, CreateNewFund::class.java),
            null
        )
    }

    fun startCreateNewEvents() {
        ContextCompat.startActivity(
            context, Intent(context, CreateNewEvents::class.java),
            null
        )
    }

    fun startCreateNewCashFlow() {
        this.startCreateNewEvents()
    }

    fun startCreateNewCategory() {
        ContextCompat.startActivity(
            context, Intent(context, CreateNewCategory::class.java),
            null
        )
    }
    fun terminateActivity(activity: Activity) {
        activity.finish()
    }

    fun startEditEvents(events: Events) {
        TODO("Not yet implemented")
    }

    fun startEditTransfers(transfers: Transfers) {
        TODO("Not yet implemented")
    }
}