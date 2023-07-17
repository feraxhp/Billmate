package com.feraxhp.billmate.layauts.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.layauts.tabs.components.home.BalanceCard
import com.feraxhp.billmate.layauts.tabs.components.home.UserMessage

@Composable
fun HomeTab(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    setScrollState: (Int) -> Unit = {},

    ) {

    val lazyListState = rememberLazyListState()
    val scrollValue by remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    setScrollState(scrollValue)

    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyListState,

        ) {
        item {
            UserMessage()
            BalanceCard(
                modifier = Modifier.padding(horizontal = 24.dp).padding(top = 24.dp),
                totalBalance = appController.getTotalBalance(),
                totalIncomes = appController.getTotalIncomes(),
                totalExpenses = appController.getTotalExpenses()
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun HomeTabPreview() {
    HomeTab()
}