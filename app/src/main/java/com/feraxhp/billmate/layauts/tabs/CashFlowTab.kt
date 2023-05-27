package com.feraxhp.billmate.layauts.tabs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.layauts.tabs.components.EventsCard

@Composable
fun CashFlowTab(padding: PaddingValues = PaddingValues(0.dp)) {
    val listIncomes by remember { mutableStateOf(MainActivity.appController.getAllIncomes()) }
    val listExpenses by remember { mutableStateOf(MainActivity.appController.getAllIncomes()) }
    LazyColumn(
        modifier = Modifier
            .padding(padding),
    ) {
        items(listIncomes.toMutableList().size) {
            EventsCard(
                type = listIncomes[it].type,
                name = listIncomes[it].name,
                amount = listIncomes[it].amount,
                date = listIncomes[it].date,
                time = listIncomes[it].time
            )
        }
    }

}

@Preview
@Composable
fun CashFlowTabPreview() {
    CashFlowTab()
}