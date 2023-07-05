package com.feraxhp.billmate.layauts.tabs

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
import com.feraxhp.billmate.extrendedFuntions.dateFormat
import com.feraxhp.billmate.layauts.tabs.components.cashflow.EventsCard
import com.feraxhp.billmate.layauts.tabs.components.components.SegmentedButtons
import com.feraxhp.billmate.layauts.tabs.components.cashflow.TransfersCard
import com.feraxhp.billmate.layauts.tabs.components.components.ConfirmationAlert

@Composable
fun CashFlowTab(
    padding: PaddingValues = PaddingValues(0.dp),
    setScrollState: (Int) -> Unit = {},
    setTitle: (String) -> Unit = {},
) {

    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(0) }
    var listTransfers by remember(key1 = selectedIndex) { mutableStateOf(appController.getAllTransfers()) }
    var listEvents by remember(key1 = selectedIndex) { mutableStateOf(appController.getAllEvents()) }
    var showDialog by remember { mutableStateOf(false) }
    var type:Boolean? by remember { mutableStateOf(null) }
    var indexToEliminate:Int? by remember { mutableStateOf(null) }

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
            if (type != null){
                ConfirmationAlert(
                    openState = showDialog,
                    setOpenState = { showDialog = it },
                    title = if (type == true) "Delete this event?" else "Delete this transfer?",
                    text = if (type == true) "If you delete this event, you will not be able to recover it" else "If you delete this transfer, you will not be able to recover it",
                    onConfirm = {
                        if (type == true) {
                            appController.removeEvent(listEvents[indexToEliminate!!])
                            listEvents = listEvents
                                .toMutableList()
                                .apply {
                                    removeAt(indexToEliminate!!)
                                }
                        }else{
                            appController.removeTransfer(listTransfers[indexToEliminate!!])
                            listTransfers = listTransfers
                                .toMutableList()
                                .apply {
                                    removeAt(indexToEliminate!!)
                                }
                        }
                        type = null
                        indexToEliminate = null
                    }
                )
            }
        }
        if (selectedIndex == 0) {
            items(listEvents.toMutableList().size) {index ->
                if (index != 0) Divider()
                EventsCard(
                    type = listEvents[index].type,
                    name = listEvents[index].name,
                    amount = listEvents[index].amount,
                    date = listEvents[index].date.dateFormat(),
                    time = listEvents[index].time,
                    onClick = {
                        showDialog = true
                        type = true
                        indexToEliminate = index
                    },
                    onBodyClick = {
                        viewController.startEditEvents(listEvents[index])
                    }
                )
            }
        } else {
            items(listTransfers.toMutableList().size) { index ->
                if (index != 0) Divider()
                TransfersCard(
                    time = listTransfers[index].time,
                    amount = listTransfers[index].amount,
                    date = listTransfers[index].date.dateFormat(),
                    origin = appController.getFundByID(listTransfers[index].origin_fund_id)!!.accountName,
                    destination = appController.getFundByID(listTransfers[index].target_fund_id)!!.accountName,
                    onClick = {
                        showDialog = true
                        type = false
                        indexToEliminate = index
                    },
                    onBodyClick = {
                        viewController.startEditTransfers(listTransfers[index])
                    }
                )
            }
        }
    }
}