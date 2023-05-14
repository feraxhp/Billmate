package com.feraxhp.billmate.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.feraxhp.billmate.controller.Controller
import com.feraxhp.billmate.layauts.screens.UpcomingScreen

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var appController: Controller
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            appController = Controller(this)
            if (appController.user.getName() == null) {
                UpcomingScreen(this)
            } else {
                startActivity(Intent(this, CommonViewActivity::class.java), null)
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}

