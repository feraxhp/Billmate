package com.feraxhp.billmate.layauts.tabs

import android.icu.util.Calendar
import android.icu.util.TimeZone
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.layauts.tabs.components.cashflow.EventsCard
import com.feraxhp.billmate.layauts.tabs.components.components.SegmentedButtons
import com.feraxhp.billmate.layauts.tabs.components.cashflow.TransfersCard
import com.feraxhp.billmate.layauts.tabs.components.components.ConfirmationAlert
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
fun CashFlowTab(
    padding: PaddingValues = PaddingValues(0.dp),
    setScrollState: (Int) -> Unit = {},
    setTitle: (String) -> Unit = {},
    goHome: () -> Unit = {},
) {

    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(0) }
    var listTransfers by remember(key1 = selectedIndex) { mutableStateOf(appController.getAllTransfers()) }
    var listEvents by remember(key1 = selectedIndex) { mutableStateOf(appController.getAllEvents()) }

    val lazyListState = rememberLazyListState()
    val scrollValue by remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    setScrollState(scrollValue)
    setTitle(
        when (selectedIndex) {
            0 -> "Events"
            1 -> "Transfers"
            else -> ""
        }
    )
    LazyColumn(
        modifier = Modifier.padding(top = 0.dp),
        verticalArrangement = Arrangement.Top,
        state = lazyListState,
        contentPadding = padding
    ) {
        item {
            SegmentedButtons(
                values = listOf("Events", "Transfers"),
                selectedValue = selectedIndex,
                setSelectedValue = setSelectedIndex,
            )
        }
        if (selectedIndex == 0) {
            items(listEvents.toMutableList().size) {index ->
                val calendar =
                    Calendar.getInstance(TimeZone.getTimeZone("UTC${ZonedDateTime.now(ZoneId.systemDefault()).offset}"))
                calendar.timeInMillis = listEvents[index].date

                // Confirmation alert dialog
                var showDialog by remember { mutableStateOf(false) }

                if (index != 0) Divider()
                EventsCard(
                    type = listEvents[index].type,
                    name = listEvents[index].name,
                    amount = listEvents[index].amount,
                    date = "${
                        calendar[Calendar.DAY_OF_MONTH]
                    }-${
                        calendar[Calendar.MONTH] + 1
                    }-${
                        calendar[Calendar.YEAR]
                    }",
                    time = listEvents[index].time,
                    description = listEvents[index].description,
                    onClick = {
                        showDialog = true
                    },
                    onBodyClick = {
                        goHome()
                        viewController.startEditEvents(listEvents[index])
                    }
                )
                ConfirmationAlert(
                    openState = showDialog,
                    setOpenState = { showDialog = it },
                    title = "Delete this event?",
                    text = "If you delete this event, you will not be able to recover it",
                    onConfirm = {
                        appController.removeEvent(listEvents[index])
                        listEvents = listEvents
                            .toMutableList()
                            .apply {
                                removeAt(index)
                            }
                    }
                )
            }
        } else {
            items(listTransfers.toMutableList().size) { index ->
                val calendar =
                    Calendar.getInstance(TimeZone.getTimeZone("UTC${ZonedDateTime.now(ZoneId.systemDefault()).offset}"))
                calendar.timeInMillis = listTransfers[index].date

                var showDialog by remember { mutableStateOf(false) }

                if (index != 0) Divider()
                TransfersCard(
                    time = listTransfers[index].time,
                    amount = listTransfers[index].amount,
                    description = listTransfers[index].description,
                    date = "${
                        calendar[Calendar.DAY_OF_MONTH]
                    }-${
                        calendar[Calendar.MONTH] + 1
                    }-${
                        calendar[Calendar.YEAR]
                    }",
                    origin = appController.getFundByID(listTransfers[index].origin_fund_id)!!.accountName,
                    destination = appController.getFundByID(listTransfers[index].target_fund_id)!!.accountName,
                    onClick = {
                        showDialog = true
                    },
                    onBodyClick = {
                        goHome()
                        viewController.startEditTransfers(listTransfers[index])
                    }
                )
                ConfirmationAlert(
                    openState = showDialog,
                    setOpenState = { showDialog = it },
                    title = "Delete this transfer?",
                    text = "If you delete this transfer, you will not be able to recover it",
                    onConfirm = {
                        appController.removeTransfer(listTransfers[index])
                        listTransfers = listTransfers
                            .toMutableList()
                            .apply {
                                removeAt(index)
                            }
                    }
                )
            }
        }
    }
}