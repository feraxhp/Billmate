package com.feraxhp.billmate.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.feraxhp.billmate.layauts.screens.NewEvents

class CreateNewEvents : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewEvents()
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}