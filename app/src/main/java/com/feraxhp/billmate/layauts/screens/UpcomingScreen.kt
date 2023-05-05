package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.feraxhp.billmate.activitys.CommonViewActivity
import com.feraxhp.billmate.logic.User
import com.feraxhp.billmate.activitys.theme.BillmateTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpcomingScreen(user: User, context: Context) {
    val (userName, setUserName) = remember { mutableStateOf("") }

    BillmateTheme {
        Surface(
            color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
//                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    val center = Modifier.align(CenterHorizontally)
                    Text(
                        text = "Welcome!!",
                        fontSize = 40.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = center
                    )
                    TextField(
                        value = userName, onValueChange = setUserName, modifier = center
                    )
                    Text(
                        text = "What's your name?", modifier = center
                    )
                    Button(
                        onClick = {
                            user.setName(if (userName == "") null else userName)
                            startActivity(context, Intent(context, CommonViewActivity::class.java), null)
                        },
                        modifier = center.padding(top = 20.dp)
                    ) {
                        Text(text = "Submit")
                    }
                }
            }
        }
    }
}