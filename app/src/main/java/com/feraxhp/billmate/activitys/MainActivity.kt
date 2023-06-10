package com.feraxhp.billmate.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            var hasName by remember { mutableStateOf(appController.user.getName() != null) }
            if (!hasName) {
                UpcomingScreen(
                    setHasName = {
                        hasName = it
                    }
                )
            } else {
                PrimaryScreen()
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}

