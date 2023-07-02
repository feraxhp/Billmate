package com.feraxhp.billmate.layauts.tabs

import android.icu.util.Calendar
import android.icu.util.TimeZone
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.ui.theme.Elevations
import com.feraxhp.billmate.layauts.tabs.components.EventsCard
import com.feraxhp.billmate.layauts.tabs.components.SegmentedButtons
import com.feraxhp.billmate.layauts.tabs.components.TransfersCard
import com.feraxhp.billmate.layauts.tabs.components.components.ConfirmationAlert
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
fun CashFlowTab(
    padding: PaddingValues = PaddingValues(0.dp),
    setScrollState: (Int) -> Unit = {},
    setTitle: (String) -> Unit = {}
) {
    var listTransfers by remember { mutableStateOf(appController.getAllTransfers()) }
    var listEvents by remember { mutableStateOf(appController.getAllEvents()) }
    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(0) }

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
            items(listEvents.toMutableList().size) {
                val calendar =
                    Calendar.getInstance(TimeZone.getTimeZone("UTC${ZonedDateTime.now(ZoneId.systemDefault()).offset}"))
                calendar.timeInMillis = listEvents[it].date

                // Confirmation alert dialog
                var showDialog by remember { mutableStateOf(false) }

                if (it != 0) Divider()
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
                    showDialog = true
                }
                ConfirmationAlert(
                    openState = showDialog,
                    setOpenState = { showDialog = it },
                    title = "Delete this event?",
                    text = "If you delete this event, you will not be able to recover it",
                    onConfirm = {
                        appController.removeEvent(listEvents[it])
                        listEvents = listEvents
                            .toMutableList()
                            .apply {
                                removeAt(it)
                            }
                    }
                )
            }
        } else {
            items(listTransfers.toMutableList().size) {
                val calendar =
                    Calendar.getInstance(TimeZone.getTimeZone("UTC${ZonedDateTime.now(ZoneId.systemDefault()).offset}"))
                calendar.timeInMillis = listTransfers[it].date

                var showDialog by remember { mutableStateOf(false) }

                if (it != 0) Divider()
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
                    showDialog = true
                }
                ConfirmationAlert(
                    openState = showDialog,
                    setOpenState = { showDialog = it },
                    title = "Delete this transfer?",
                    text = "If you delete this transfer, you will not be able to recover it",
                    onConfirm = {
                        appController.removeTransfer(listTransfers[it])
                        listTransfers = listTransfers
                            .toMutableList()
                            .apply {
                                removeAt(it)
                            }
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun CashFlowTabPreview() {
    CashFlowTab()
}