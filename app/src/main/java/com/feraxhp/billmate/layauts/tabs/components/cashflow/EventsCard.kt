package com.feraxhp.billmate.layauts.tabs.components.cashflow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.feraxhp.billmate.extrendedFuntions.toPointingString

@Composable
fun EventsCard(
    type: Boolean = false,
    name: String = "Example",
    amount: Double = 0.0,
    description: String = "",
    date: String = "2019-08-26",
    time: String = "15:00",
    onClick: () -> Unit = {},
    onBodyClick: () -> Unit = {}
) {
    val color = if (type) Color(0XFF008C37) else Color(0xFFff0000)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = if (type) painterResource(
                id = R.drawable.baseline_input_24
            ) else painterResource(
                id = R.drawable.baseline_output_24
            ),
            contentDescription = "",
            tint = color,
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp)
        )
        Column(
            modifier = Modifier
                .clickable(indication = null, interactionSource = MutableInteractionSource()) {
                    onBodyClick()
                }
                .weight(2f)
        ) {
            Text(
                text = "$name: ${amount.toPointingString()}",
                fontSize = 20.sp,
                color = color
            )
            Text(
                text = "${if (description == "") "No description" else description} ~ $date ~ $time",
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = if (type) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "",
                tint = color,
                modifier = Modifier
                    .size(60.dp)
//                    .padding(10.dp)
            )
        }

    }
}

@Preview
@Composable
fun EventsCardPreview() {
    EventsCard()
}