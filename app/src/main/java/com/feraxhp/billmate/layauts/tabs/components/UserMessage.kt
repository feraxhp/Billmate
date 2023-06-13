package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController

@Composable
fun UserMessage() {
    val changeName = remember { mutableStateOf(false) }
    val (newName, setNewName) = remember(key1 = changeName.value) { mutableStateOf("") }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(start = 24.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(percent = 50),
                )
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Column(
            Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
                .padding(end = 24.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .clickable(indication = null, interactionSource = MutableInteractionSource()) {
                    changeName.value = true
                }
        ) {
            Text(
                text = "${appController.user.getName()}",
                fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.3,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                modifier = Modifier.padding(start = 12.dp)
            )
            Text(
                text = if(appController.user.getName()=="YaaD") "I LOVE YOU!!" else "Welcome back!",
                modifier = Modifier.padding(start = 12.dp)

            )
        }
    }
    if (changeName.value) {
        AlertDialog(
            onDismissRequest = {
                changeName.value = false
            },
            title = {
                Text(text = "Change name:")
            },
            text = {
                OutlinedTextField(
                    value = newName,
                    onValueChange = setNewName,
                    label = { Text("New Name") },
                    shape = MaterialTheme.shapes.small,
                    placeholder = {
                        appController.user.getName()
                            ?.let { Text(text = it) }
                    })
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (newName != "") {
                            appController.changeFundsDefaultTitularName(appController.user.getName()!!, newName)
                            appController.user.setName(newName)
                        }
                        changeName.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        changeName.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
fun UserMessagePreview() {
    UserMessage()
}