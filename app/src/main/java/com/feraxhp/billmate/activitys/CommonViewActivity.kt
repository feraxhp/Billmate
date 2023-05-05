package com.feraxhp.billmate.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.activity.compose.setContent
import com.feraxhp.billmate.layauts.screens.PrimaryUi

class CommonViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrimaryUi()
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}