@file:OptIn(ExperimentalMaterial3Api::class)

package com.feraxhp.billmate

import PrimaryUi
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.feraxhp.billmate.structure.*

class MainActivity : ComponentActivity() {
    //    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrimaryUi()
        }
    }

    var prueba = Fund(100.0, "No se")
}

