package com.feraxhp.billmate.layauts.tabs.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat
import com.feraxhp.billmate.layauts.tabs.components.components.MyCards


@Composable
fun BalanceCard(
    modifier: Modifier = Modifier,
    totalBalance: Double = 0.0,
    totalIncomes: Double = 0.0,
    totalExpenses: Double = 0.0
) {
    val onColor = MaterialTheme.colorScheme.onPrimaryContainer
    val colorStops = arrayOf(
        0.0f to MaterialTheme.colorScheme.primaryContainer,
        0.45f to MaterialTheme.colorScheme.secondaryContainer,
        0.55f to MaterialTheme.colorScheme.secondaryContainer,
        1f to MaterialTheme.colorScheme.tertiaryContainer
    )
    MyCards(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(250.dp)
            .then(modifier)
            .clip(MaterialTheme.shapes.large)
        ,
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(colorStops = colorStops)
                )
        ) {
            Column() {
                Text(
                    text = "Total Balance:",
                    color = onColor,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 24.dp)
                )
                Text(
                    text = totalBalance.toMoneyFormat(),
                    color = onColor,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 8.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .padding(vertical = 24.dp)
            ) {
                Column(
//                    modifier = Modifier.weight(1f)
                ){
                    Text(
                        text = "Total incomes:",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        color = onColor,
                    )
                    Text(
                        text = totalIncomes.toMoneyFormat(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        color = onColor,
                    )
                }
                Column (
//                    modifier = Modifier.weight(1f)
                ){
                    Text(
                        text = "Total expenses:",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        color = onColor,
                    )
                    Text(
                        text = totalExpenses.toMoneyFormat(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        color = onColor,
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun BalanceCardPreview() {
    BalanceCard()
}