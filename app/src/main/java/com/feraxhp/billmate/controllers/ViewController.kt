package com.feraxhp.billmate.controllers

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.feraxhp.billmate.activitys.CreateNewCategory
import com.feraxhp.billmate.activitys.CreateNewEvents
import com.feraxhp.billmate.activitys.CreateNewFund
import com.feraxhp.billmate.activitys.EditEvents
import com.feraxhp.billmate.activitys.EditTransfers
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.controllers.dependencies.Activities
import com.feraxhp.billmate.logic_database.database.entities.Events
import com.feraxhp.billmate.logic_database.database.entities.Transfers

class ViewController(private val fatherContext: Context) {
    var event2Edit: Events? = null
    var transfer2Edit: Transfers? = null



    private val fatherActivity = fatherContext as Activity

    private val activitiesMap = mapOf(
        Activities.main to MainActivity::class.java,
        Activities.createNewCategory to CreateNewCategory::class.java,
        Activities.createNewEvents to CreateNewEvents::class.java,
        Activities.createNewFund to CreateNewFund::class.java,
        Activities.editEvents to EditEvents::class.java,
        Activities.editTransfers to EditTransfers::class.java
    )

    fun startActivity(activityKey: Activities) {
        ContextCompat.startActivity(
            fatherContext, Intent(fatherContext, activitiesMap[activityKey]),
            null
        )
    }

    @SuppressLint("ComposableNaming")
    @Composable
    fun finishActivity() {
        (LocalContext.current as Activity).finish()
    }
    @SuppressLint("ComposableNaming")
    @Composable
    fun finishActivityWithActualize() {
        this.event2Edit = null
        this.transfer2Edit = null
        this.finishActivity()
        this.startActivity(Activities.main)
        fatherActivity.finish()
    }


//    fun startMainActivity() {
//        ContextCompat.startActivity(
//            context, Intent(context, MainActivity::class.java),
//            null
//        )
//    }
//
//    fun startCreateNewFund() {
//        ContextCompat.startActivity(
//            context, Intent(context, CreateNewFund::class.java),
//            null
//        )
//    }
//
//    fun startCreateNewEvents() {
//        ContextCompat.startActivity(
//            context, Intent(context, CreateNewEvents::class.java),
//            null
//        )
//    }
//
//    fun startCreateNewCashFlow() {
//        this.startCreateNewEvents()
//    }
//
//    fun startCreateNewCategory() {
//        ContextCompat.startActivity(
//            context, Intent(context, CreateNewCategory::class.java),
//            null
//        )
//    }
//    fun terminateActivity(activity: Activity) {
//        activity.finish()
//    }
//    fun terminateActivityWithActualize(activity: Activity) {
//        activity.finish()
//        startMainActivity()
//    }
//
//    fun startEditEvents(event: Events) {
//        this.event2Edit = event
//        ContextCompat.startActivity(
//            context, Intent(context, EditEvents::class.java),
//            null
//        )
//    }
//
//    fun startEditTransfers(transfer: Transfers) {
//        this.transfer2Edit = transfer
//        ContextCompat.startActivity(
//            context, Intent(context, EditTransfers::class.java),
//            null
//        )
//    }


}