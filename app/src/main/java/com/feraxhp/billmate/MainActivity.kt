package com.feraxhp.billmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.feraxhp.billmate.layauts.screens.PrimaryUi

class MainActivity : ComponentActivity() {
    //    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrimaryUi()
        }
    }
}

