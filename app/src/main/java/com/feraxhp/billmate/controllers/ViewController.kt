package com.feraxhp.billmate.controllers

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.feraxhp.billmate.activitys.newItems.CreateNewCategoryActivity
import com.feraxhp.billmate.activitys.newItems.CreateNewEventsActivity
import com.feraxhp.billmate.activitys.newItems.CreateNewFundActivity
import com.feraxhp.billmate.activitys.editItems.EditEventsActivity
import com.feraxhp.billmate.activitys.editItems.EditTransfersActivity
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.activitys.editItems.EditFundsActivity
import com.feraxhp.billmate.activitys.getFilesActivity
import com.feraxhp.billmate.controllers.dependencies.Activities
import com.feraxhp.billmate.logic_database.database.entities.Categories
import com.feraxhp.billmate.logic_database.database.entities.Events
import com.feraxhp.billmate.logic_database.database.entities.Funds
import com.feraxhp.billmate.logic_database.database.entities.Transfers

class ViewController(
    val fatherContext: Context,
) {
    var fund2Edit: Funds? = null
    var category2Edit: Categories? = null
    var event2Edit: Events? = null
    var transfer2Edit: Transfers? = null

    private val fatherActivity = fatherContext as Activity

    private val activitiesMap = mapOf(
        Activities.main to MainActivity::class.java,
        Activities.createNewCategory to CreateNewCategoryActivity::class.java,
        Activities.createNewEvents to CreateNewEventsActivity::class.java,
        Activities.createNewFund to CreateNewFundActivity::class.java,
        Activities.editEvents to EditEventsActivity::class.java,
        Activities.editTransfers to EditTransfersActivity::class.java,
        Activities.editFunds to EditFundsActivity::class.java,
        Activities.getFiles to getFilesActivity::class.java
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
        this.cleanVariables()
        this.finishActivity()
        this.startActivity(Activities.main)
        fatherActivity.finish()
    }

    fun finishActivityWithActualize(context: Context) {
        this.cleanVariables()
        (context as Activity).finish()
        this.startActivity(Activities.main)
        fatherActivity.finish()
    }

    private fun cleanVariables() {
        this.fund2Edit = null
        this.category2Edit = null
        this.event2Edit = null
        this.transfer2Edit = null
    }
}