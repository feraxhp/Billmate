package com.feraxhp.billmate.activitys

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.drawable.AdaptiveIconDrawable
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.feraxhp.billmate.ui.theme.BillmateTheme
import com.feraxhp.billmate.controllers.AppController
import com.feraxhp.billmate.controllers.ViewController
import com.feraxhp.billmate.layauts.screens.PrimaryScreen
import com.feraxhp.billmate.layauts.screens.UpcomingScreen

class MainActivity : ComponentActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var viewController: ViewController
        @SuppressLint("StaticFieldLeak")
        lateinit var appController: AppController

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setDinamicColorToAppIcon()

        setContent {

            appController = AppController(this)
            viewController = ViewController(this)

            var hasName by remember { mutableStateOf(appController.user.getName() != null) }
            BillmateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (!hasName) UpcomingScreen { hasName = it }
                    else PrimaryScreen()
                }
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
    private fun setDinamicColorToAppIcon() {
        val value = TypedValue()
        theme.resolveAttribute(android.R.attr.colorPrimary, value, true)
        val colorPrimary = value.data
        try {
            val drawable = packageManager.getApplicationIcon(packageName)
            val adaptiveIconDrawable = drawable as? AdaptiveIconDrawable
            adaptiveIconDrawable?.setTint(colorPrimary)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

}

