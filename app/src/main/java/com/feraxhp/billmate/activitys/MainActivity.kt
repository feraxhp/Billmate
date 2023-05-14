package com.feraxhp.billmate.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.feraxhp.billmate.controller.AppController
import com.feraxhp.billmate.controller.ViewController
import com.feraxhp.billmate.layauts.screens.PrimaryScreen
import com.feraxhp.billmate.layauts.screens.UpcomingScreen

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var appController: AppController
        lateinit var viewController: ViewController
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            appController = AppController(this)
            viewController = ViewController(this)
            if (appController.user.getName() == null) {
                UpcomingScreen(this)
            } else {
                PrimaryScreen()
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}

