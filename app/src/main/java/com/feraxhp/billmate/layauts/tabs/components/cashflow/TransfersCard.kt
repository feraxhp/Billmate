package com.feraxhp.billmate.layauts.tabs.components.cashflow

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feraxhp.billmate.R
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.extrendedFuntions.timeFormat
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat

@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransfersCard(
    amount: Double = 0.0,
    origin: String = "Example",
    destination: String = "Example",
    date: String = "2019-08-26",
    time: String = "16:00",
    onClick: () -> Unit = {},
    onBodyClick: () -> Unit = {}
) {
    val color by  remember { mutableStateOf(Color(0xFF008C8C))}

    val amount_ by remember { mutableStateOf(amount) }
    val date_ by remember { mutableStateOf(date) }
    val time_ by remember { mutableStateOf(time) }
    val origin_ by remember { mutableStateOf(origin) }
    val destination_ by remember { mutableStateOf(destination) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val state = rememberTimePickerState()
        Icon(
            painter = painterResource(id = appController.icons.getResourceId(2122, 0)),
            contentDescription = "",
            tint = color,
            modifier = Modifier
                .size(60.dp)
                .padding(horizontal = 10.dp)
        )
        Column(
            modifier = Modifier
                .clickable(indication = null, interactionSource = MutableInteractionSource()) {
                    onBodyClick()
                }
                .weight(2f)
        ) {
            Text(text = "$origin_ ➤ $destination_: ${amount_.toMoneyFormat()}",
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = color)
            Text(text = "$date_ ~ ${time_.timeFormat(state.is24hour)}", color = MaterialTheme.colorScheme.onBackground)
        }
        IconButton(onClick = { onClick() }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 10.dp),
                tint = color
            )
        }

    }
}

@Preview
@Composable
fun TransfersCardPreview() {
    TransfersCard()
}