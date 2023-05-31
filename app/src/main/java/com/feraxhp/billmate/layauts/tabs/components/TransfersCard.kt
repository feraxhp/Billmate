package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import com.feraxhp.billmate.R

@Composable
fun TransfersCard(
    amount: Double = 0.0,
    origin: String = "Example",
    destination: String = "Example",
    description: String = "Example",
    date: String = "2019-08-26",
    time: String = "16:00",
    onClick: () -> Unit = {}
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
            modifier = Modifier.padding(horizontal = 10.dp),
            tint = color
        )
        Column(modifier = Modifier.weight(2f)) {
            Text(text = "$origin -> $destination: $amount", color = color)
            Text(text = "$description // $date ~ $time", color = MaterialTheme.colorScheme.onBackground)
        }
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
//                .weight(1f)
        ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                        .padding(end = 5.dp),
                    tint = color
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
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