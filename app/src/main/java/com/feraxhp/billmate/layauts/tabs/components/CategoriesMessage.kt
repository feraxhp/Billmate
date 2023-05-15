package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoriesMessage(name: String = "Example", amount: Double = 0.0) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                color = MaterialTheme.colorScheme.primary,
                width = 1.dp,
                shape = MaterialTheme.shapes.small
                )
            .padding(10.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "",
            modifier = Modifier
                .padding(10.dp)
        )
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 10.dp),
        ) {
            Text(text = name, fontSize = 20.sp)
            Text(text = "Amount: $amount", fontSize = 14.sp)
        }
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "",
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Preview
@Composable
fun CategoriesMessagePreview() {
    CategoriesMessage()
}