package com.feraxhp.billmate.layauts.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.layauts.tabs.components.BalanceCard
import com.feraxhp.billmate.layauts.tabs.components.ExpensesCard
import com.feraxhp.billmate.layauts.tabs.components.UserMessage

@Composable
fun HomeTab() {
    Column {
        UserMessage()
        BalanceCard()
        ExpensesCard()
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun HomeTabPreview() {
    HomeTab()
}