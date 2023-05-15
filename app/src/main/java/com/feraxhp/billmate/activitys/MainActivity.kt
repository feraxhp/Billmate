package com.feraxhp.billmate.activitys

import android.annotation.SuppressLint
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
        @SuppressLint("StaticFieldLeak")
        lateinit var viewController: ViewController
        lateinit var appController: AppController
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

