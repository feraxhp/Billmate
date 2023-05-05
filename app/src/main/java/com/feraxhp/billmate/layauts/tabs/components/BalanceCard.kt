package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.layauts.tabs.components.components.MyCards


@Composable
fun BalanceCard() {
    MyCards {
        Text(text = "Total Balance")
        Text(text = "")
    }
}

@Preview
@Composable
fun BalanceCardPreview() {
    BalanceCard()
}