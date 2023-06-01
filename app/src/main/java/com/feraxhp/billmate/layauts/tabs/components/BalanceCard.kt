package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.layauts.tabs.components.components.MyCards


@Composable
fun BalanceCard() {
    val onColor = MaterialTheme.colorScheme.onPrimaryContainer
    MyCards(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .height(200.dp)
            .clip(MaterialTheme.shapes.large),
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Text(
            text = "Total Balance:",
            color = onColor,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp)
        )
        Text(
            text = "${appController.getTotalBalance()}",
            color = onColor,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 10.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(vertical = 24.dp)
        ) {
            Text(
                text = "Incomes: ${appController.getTotalIncomes()}",
                color = onColor
            )
            Text(
                text = "Expenses: ${appController.getTotalExpenses()}",
                color = onColor
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun BalanceCardPreview() {
    BalanceCard()
}