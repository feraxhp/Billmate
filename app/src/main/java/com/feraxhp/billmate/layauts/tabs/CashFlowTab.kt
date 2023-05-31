package com.feraxhp.billmate.layauts.tabs

import android.icu.util.Calendar
import android.icu.util.TimeZone
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.layauts.screens.components.SegmentedButtons
import com.feraxhp.billmate.layauts.tabs.components.EventsCard
import com.feraxhp.billmate.layauts.tabs.components.TransfersCard
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
fun CashFlowTab(padding: PaddingValues = PaddingValues(0.dp)) {
    var listTransfers by remember { mutableStateOf(appController.getAllTransfers()) }
    var listEvents by remember { mutableStateOf(appController.getAllEvents()) }
    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(padding)
    ) {
        SegmentedButtons(
            buttonNames = listOf("Events", "Transfers"),
            selectedValue = selectedIndex,
            onItemClick = setSelectedIndex
        )
        LazyColumn(
            modifier = Modifier.padding(top = 0.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            if (selectedIndex == 0) {
                items(listEvents.toMutableList().size) {
                    val calendar =
                        Calendar.getInstance(TimeZone.getTimeZone("UTC${ZonedDateTime.now(ZoneId.systemDefault()).offset}"))
                    calendar.timeInMillis = listEvents[it].date
                    EventsCard(
                        type = listEvents[it].type,
                        name = listEvents[it].name,
                        amount = listEvents[it].amount,
                        date = "${
                            calendar[Calendar.DAY_OF_MONTH]
                        }-${
                            calendar[Calendar.MONTH] + 1
                        }-${
                            calendar[Calendar.YEAR]
                        }",
                        time = listEvents[it].time,
                        description = listEvents[it].description
                    ) {
                        appController.removeEvent(listEvents[it])
                        listEvents = listEvents
                            .toMutableList()
                            .apply { removeAt(it) }
                    }
                }
            } else {
                items(listTransfers.toMutableList().size) {
                    val calendar =
                        Calendar.getInstance(TimeZone.getTimeZone("UTC${ZonedDateTime.now(ZoneId.systemDefault()).offset}"))
                    calendar.timeInMillis = listTransfers[it].date
                    TransfersCard(
                        time = listTransfers[it].time,
                        amount = listTransfers[it].amount,
                        description = listTransfers[it].description,
                        date = "${
                            calendar[Calendar.DAY_OF_MONTH]
                        }-${
                            calendar[Calendar.MONTH] + 1
                        }-${
                            calendar[Calendar.YEAR]
                        }",
                        origin = appController.getFundByID(listTransfers[it].origin_fund_id)!!.accountName,
                        destination = appController.getFundByID(listTransfers[it].target_fund_id)!!.accountName,
                    ) {
                        appController.removeTransfer(listTransfers[it])
                        listTransfers = listTransfers
                            .toMutableList()
                            .apply {
                                removeAt(it)
                            }
                    }
                }
            }
        }
    }


}

@Preview
@Composable
fun CashFlowTabPreview() {
    CashFlowTab()
}