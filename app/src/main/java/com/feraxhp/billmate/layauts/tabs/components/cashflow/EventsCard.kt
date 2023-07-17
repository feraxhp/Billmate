package com.feraxhp.billmate.layauts.tabs.components.cashflow

import android.annotation.SuppressLint
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
import androidx.compose.runtime.setValue
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
fun EventsCard(
    type: Boolean = false,
    name: String = "example",
    amount: Double = 3000000.0,
    date: String = "2019-08-26",
    time: String = "15:00",
    onClick: @Composable () -> Unit = {},
    onBodyClick: @Composable () -> Unit = {}
) {
    val color by remember {mutableStateOf(if (type) Color(0XFF008C37) else Color(0xFFff0000))}

    var isClicked by remember { mutableStateOf(false) }
    if (isClicked) onClick(); isClicked = false

    var isBodyClicked by remember { mutableStateOf(false) }
    if (isBodyClicked) onBodyClick(); isBodyClicked = false

    val type_ by remember {mutableStateOf(type)}
    val name_ by remember {mutableStateOf(name)}
    val amount_ by remember {mutableStateOf(amount)}
    val date_ by remember {mutableStateOf(date)}
    val time_ by remember {mutableStateOf(time)}



    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val state = rememberTimePickerState()
        Icon(
            painter = if (type_) painterResource(
                id = appController.icons.getResourceId(2120, 0)
            ) else painterResource(
                id = appController.icons.getResourceId(2121, 0)
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
                    isBodyClicked = true
                }
                .weight(2f)
        ) {
            Row(
                Modifier
                    .heightIn(0.dp, 28.dp)
                    .wrapContentWidth()
            ) {
                Text(
                    text = name_,
                    fontSize = 20.sp,
                    color = color,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    softWrap = false,
                    modifier = Modifier
                        .widthIn(0.dp, 100.dp)
                )
                Text(
                    text = ": ${amount_.toMoneyFormat()}",
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = color,
                    modifier = Modifier
                        .weight(1f)
                )
            }

            Text(
                text = "${date_} ~ ${time_.timeFormat(state.is24hour)}",
                maxLines = 1,
                color = MaterialTheme.colorScheme.onBackground,

                )

        }
        IconButton(
            onClick = { isClicked = true },
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "",
                tint = color,
                modifier = Modifier
                    .size(20.dp)
            )
        }

    }
}

@Preview
@Composable
fun EventsCardPreview() {
    EventsCard()
}