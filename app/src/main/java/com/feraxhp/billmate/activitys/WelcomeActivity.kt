package com.feraxhp.billmate.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.feraxhp.billmate.layauts.screens.UpcomingScreen
import com.feraxhp.billmate.logic.User

class WelcomeActivity : ComponentActivity() {
    companion object {
        lateinit var user: User
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            user = User(this)
            if (user.getName() == null) {
                UpcomingScreen(user, this)
            }else{
                startActivity(Intent(this, CommonViewActivity::class.java),null)
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}

