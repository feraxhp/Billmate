package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.controller

@Composable
fun UserMessage() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Person, contentDescription = "",
            modifier = Modifier
                .padding(start = 15.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(percent = 50),
                )
                .padding(7.dp)
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
        )
        Column(
            Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "${controller.user.getName()}",
                fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.3,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                modifier = Modifier.padding(start = 15.dp)
            )
            Text(
                text = "Welcome back!",
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

@Preview
@Composable
fun UserMessagePreview() {
    UserMessage()
}