package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feraxhp.billmate.R
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpcomingScreen(
    setHasName: (Boolean) -> Unit = {},
) {
    val (userName, setUserName) = remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_stat_name
            ),
            contentDescription = "App Icon",
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .align(CenterHorizontally)
                .size(200.dp)
                .padding(top = 50.dp, bottom = 0.dp)
        )
        Column(
            modifier = Modifier
                .padding(top = 0.dp)
                .align(CenterHorizontally)
                .fillMaxWidth()
        ) {
            val center = Modifier.align(CenterHorizontally)
            Text(
                text = "Welcome!!",
                fontSize = 35.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = center.padding(bottom = 24.dp)
            )
            OutlinedTextField(
                value = userName,
                onValueChange = {
                    setUserName(it)
                    isError = false
                },
                modifier = center,
                isError = isError,
                label = {
                    if (isError) {
                        Text(text = "Your name can't be empty")
                    } else {
                        Text(
                            text = "Enter your name?"
                        )
                    }
                },
                colors = TextFieldDefaults.colors(

                )
            )
            Button(
                onClick = {
                    if (userName == "") {
                        isError = true
                    } else {
                        appController.user.setName(userName)
                        setHasName(true)
                    }
                },
                modifier = center.padding(top = 20.dp)
            ) {
                Text(text = "Confirm")
            }
        }
    }
}
