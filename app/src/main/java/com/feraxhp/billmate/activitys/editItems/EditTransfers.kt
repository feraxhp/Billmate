package com.feraxhp.billmate.activitys.editItems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.layauts.screens.featuresEditors.EditTransfer
import com.feraxhp.billmate.ui.theme.BillmateTheme

class EditTransfers : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BillmateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainActivity.viewController.transfer2Edit?.let { EditTransfer(it) }
                }
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}