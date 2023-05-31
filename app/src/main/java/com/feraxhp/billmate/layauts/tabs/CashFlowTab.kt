package com.feraxhp.billmate.layauts.tabs

import android.icu.util.Calendar
import android.icu.util.TimeZone
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.layauts.tabs.components.EventsCard
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
fun CashFlowTab(padding: PaddingValues = PaddingValues(0.dp)) {
    var listEvents by remember { mutableStateOf(MainActivity.appController.getAllEvents()) }
    LazyColumn(
        modifier = Modifier
            .padding(padding),
    ) {
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
    }

}

@Preview
@Composable
fun CashFlowTabPreview() {
    CashFlowTab()
}