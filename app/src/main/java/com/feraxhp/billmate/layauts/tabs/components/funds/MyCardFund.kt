package com.feraxhp.billmate.layauts.tabs.components.funds

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
@Composable
fun MyCardFund(
    modifier: Modifier = Modifier,
    accountName: String = "account name",
    balance: String = "0.0",
    titularName: String = "titular name",
    description: String = "description",
    type: Int = 1,
    onClick: () -> Unit = {},
    onBodyClick: @Composable () -> Unit = {}
) {
    val types = listOf(
        "Normal",
        "Saves",
        "Loans"
    )
    val colors = listOf(
        MaterialTheme.colorScheme.onSecondary,
        MaterialTheme.colorScheme.onTertiary,
        MaterialTheme.colorScheme.onTertiary
    )
    val colorsLeftColors = listOf(
        MaterialTheme.colorScheme.onPrimaryContainer,
        MaterialTheme.colorScheme.onSecondaryContainer,
        MaterialTheme.colorScheme.onTertiaryContainer
    )

    var isBodyClicked by remember { mutableStateOf(false) }

    if (isBodyClicked) onBodyClick(); isBodyClicked = false

    val padding = 24.dp

    val startFloat = 0.2f
    val endFloat = 1f

    val brushes =  listOf(
        Brush.linearGradient(
            startFloat to MaterialTheme.colorScheme.primaryContainer,
            endFloat to MaterialTheme.colorScheme.secondary,
            start = Offset.Zero,
            end = Offset.Infinite
        ),
        Brush.linearGradient(
            startFloat to MaterialTheme.colorScheme.secondaryContainer,
            endFloat to MaterialTheme.colorScheme.tertiary,
            start = Offset.Zero,
            end = Offset.Infinite
        ),
        Brush.linearGradient(
            startFloat to MaterialTheme.colorScheme.tertiaryContainer,
            endFloat to MaterialTheme.colorScheme.primary,
            start = Offset.Zero,
            end = Offset.Infinite
        )
    )
    Card(
        modifier = modifier
//            .padding(padding * 0.2f)
            .fillMaxWidth()
//            .padding(padding * 0.2f)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = CardDefaults.shape
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = brushes[type],
                    shape = CardDefaults.shape
                )
                .clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource(),
                ){
                    isBodyClicked = true
                }
                ,
            contentAlignment = Alignment.TopStart
        ) {

            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "",
                tint = colors[type],
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable(indication = null, interactionSource = MutableInteractionSource()) {
                        onClick()
                    }
                    .padding(top = padding * 1.5f, end = padding)
                    .size(18.dp)
            )
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                Text(text = accountName, fontSize = 18.sp, modifier = Modifier, color = colorsLeftColors[type])
                Text(text = titularName, fontSize = 14.sp, modifier = Modifier, color = colorsLeftColors[type])
            }
            Text(
                text = description,
                fontSize = 12.sp,
                color = colorsLeftColors[type],
                modifier = Modifier
                    .padding(start = padding, top = padding)
                    .align(Alignment.CenterStart)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(top = padding * 5)
                    .padding(padding),
            ) {
                Text(
                    text = "Balance: $balance",
                    color = colorsLeftColors[type],
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = types[type],
                    color = colors[type],
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Preview
@Composable
fun MyCardsFundsPreview() {
    MyCardFund()
}