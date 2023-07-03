package com.feraxhp.billmate.layauts.tabs.components.cashflow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
fun TransfersCard(
    amount: Double = 0.0,
    origin: String = "Example",
    destination: String = "Example",
    description: String = "",
    date: String = "2019-08-26",
    time: String = "16:00",
    onClick: () -> Unit = {},
    onBodyClick: () -> Unit = {}
) {
    val color = Color(0xFF008C8C)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_sync_alt_24),
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
            Text(text = "$origin ➤ $destination: ${amount.toPointingString()}",
                fontSize = 20.sp,
                color = color)
            Text(text = "${if (description == "") "No description" else description} ~ $date ~ $time", color = MaterialTheme.colorScheme.onBackground)
        }
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
        ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 5.dp),
                    tint = color
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(start = 5.dp),
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