package com.feraxhp.billmate.layauts.tabs.components.cashflow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feraxhp.billmate.R
import com.feraxhp.billmate.extrendedFuntions.noDescrition
import com.feraxhp.billmate.extrendedFuntions.timeFormat
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsCard(
    type: Boolean = false,
    name: String = "example",
    amount: Double = 3000000.0,
    description: String = "describcion muy larga para probar como se comporta wadawdfawfawfawfafwafwsdfawdadawd",
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
        val state = rememberTimePickerState()
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
            Row(
                Modifier
                    .heightIn(0.dp, 28.dp)
                    .wrapContentWidth()
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    color = color,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    softWrap = false,
                    modifier = Modifier
                        .widthIn(0.dp, 150.dp)
                )
                Text(
                    text = ": ${amount.toMoneyFormat()}",
                    fontSize = 20.sp,
                    color = color,
                    modifier = Modifier
                        .weight(1f)
                )
            }
            Row(
                Modifier.fillMaxWidth()
            ) {
                Text(
                    text = description.noDescrition(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .widthIn(0.dp, 80.dp)
                )
                Text(
                    text = " ~ ",
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "$date ~ ${time.timeFormat(state.is24hour)}",
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onBackground,

                    )
            }
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
            )
        }

    }
}

@Preview
@Composable
fun EventsCardPreview() {
    EventsCard()
}