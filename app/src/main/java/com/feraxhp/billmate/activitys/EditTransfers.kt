package com.feraxhp.billmate.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.feraxhp.billmate.layauts.screens.featuresEditors.EditTransfer

class EditTransfers : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivity.viewController.transfer2Edit?.let { EditTransfer(it) }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}