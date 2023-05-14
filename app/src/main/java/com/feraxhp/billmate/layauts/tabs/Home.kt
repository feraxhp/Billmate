package com.feraxhp.billmate.layauts.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.layauts.tabs.components.BalanceCard
import com.feraxhp.billmate.layauts.tabs.components.UserMessage

@Composable
fun HomeTab(innerPadding: PaddingValues = PaddingValues(0.dp)) {
    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            UserMessage()
            BalanceCard()
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun HomeTabPreview() {
    HomeTab()
}