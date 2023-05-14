package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.layauts.tabs.components.components.MyCards


@Composable
fun BalanceCard() {
    MyCards(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(150.dp),
        color = MaterialTheme.colorScheme.background,
    ) {
        Text(
            text = "Total Balance: \n ${appController.getTotalBalance()}",
            modifier = Modifier
                .padding(15.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 40.dp)
        ) {
            val text: List<String> = listOf("Income", "Expenses")
            text.forEach {
                Text(text = "$it: ${0.00}")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun BalanceCardPreview() {
    BalanceCard()
}