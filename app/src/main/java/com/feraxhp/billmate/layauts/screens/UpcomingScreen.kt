package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.textButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.feraxhp.billmate.R
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.controllers.dependencies.Activities

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpcomingScreen(
    setHasName: (Boolean) -> Unit = {},
) {
    val (userName, setUserName) = remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Box(modifier = Modifier) {

        TextButton(
            colors = textButtonColors(
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            onClick = { viewController.startActivity(Activities.getFiles) },
            modifier = Modifier
                .align(TopEnd)
                .zIndex(2f)
                .padding(top = 24.dp)
        ) {
            Text(text = "Restore backup", modifier = Modifier.padding(end = 8.dp))
            Icon(appController.getIcon(1647), contentDescription = "")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to MaterialTheme.colorScheme.primaryContainer,
                            0.3f to MaterialTheme.colorScheme.tertiaryContainer,
                            0.7f to MaterialTheme.colorScheme.primaryContainer,
                            0.9f to MaterialTheme.colorScheme.tertiaryContainer
                        )
                    )
                )
                .padding(top = 24.dp)
                .zIndex(1f)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.manual_ic_stat_name
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
                        setUserName(it.replace("\n", ""))
                        isError = false
                    },
                    maxLines = 1,
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
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                            alpha = 0f
                        ),
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                            alpha = 0f
                        ),
                        errorContainerColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                            alpha = 0f
                        ),
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        errorTextColor = MaterialTheme.colorScheme.onErrorContainer,
                        cursorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0f
                        )
                    ),
                    onClick = {
                        if (userName == "") {
                            isError = true
                        } else {
                            appController.user.setName(userName)
                            setHasName(true)
                        }
                    },
                    modifier = center
                        .padding(top = 20.dp)
                        .shadow(
                            elevation = 7.dp,
                            spotColor = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(50)
                        )
                        .border(
                            width = 2.5.dp,
                            brush = Brush.linearGradient(
                                colorStops = arrayOf(
                                    0.0f to MaterialTheme.colorScheme.onPrimaryContainer,
                                    0.3f to MaterialTheme.colorScheme.onTertiaryContainer,
                                    0.7f to MaterialTheme.colorScheme.onPrimaryContainer,
                                    0.9f to MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            ),
                            shape = RoundedCornerShape(50)
                        )
                        .clip(RoundedCornerShape(50))


                ) {
                    Text(
                        text = "Confirm",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
