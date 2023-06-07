package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feraxhp.billmate.R
import com.feraxhp.billmate.controller.toPointingString

@Composable
fun EventsCard(
    type: Boolean = false,
    name: String = "Example",
    amount: Double = 0.0,
    description: String = "Example",
    date: String = "2019-08-26",
    time: String = "15:00",
    onClick: () -> Unit = {}
) {
    val color = if (type) Color(0XFF008C37) else Color(0xFFff0000)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = if (type) painterResource(id = R.drawable.baseline_done_all_24) else painterResource(
                id = R.drawable.baseline_clear_24
            ),
            contentDescription = "",
            tint = color,
            modifier = Modifier
                .padding(10.dp)
        )
        Column(
            modifier = Modifier
                .weight(2f)
        ) {
            Text(
                text = "$name: ${amount.toPointingString()}",
                fontSize = 20.sp,
                color = color
            )
            Text(
                text = " ${if (description == "") "Description" else description} ~ $date ~ $time",
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        IconButton(onClick = { onClick() }) {
            Icon(
                imageVector = if (type) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "",
                tint = color,
                modifier = Modifier
                    .padding(10.dp)
            )
        }

    }
}

@Preview
@Composable
fun EventsCardPreview() {
    EventsCard()
}