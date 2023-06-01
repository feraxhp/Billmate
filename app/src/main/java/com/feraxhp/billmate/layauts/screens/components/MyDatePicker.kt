package com.feraxhp.billmate.layauts.screens.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.ZoneId
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePicker(
    state: DatePickerState,
    setState: (DatePickerState) -> Unit = {},
    setDialog: (Boolean) -> Unit = {}
) {
    val rechargeable = remember {
        mutableStateOf(0)
    }
    val offset: Long = "${ZonedDateTime.now(ZoneId.systemDefault()).offset}".split(":")[0].toLong()
    val selectedDateMillis =
        if (rechargeable.value != 0) {
            System.currentTimeMillis() + (3600000 * offset)
        } else state.selectedDateMillis

    val internalState by remember(key1 = rechargeable.value) {
        mutableStateOf(
            DatePickerState(
                initialSelectedDateMillis = selectedDateMillis,
                initialDisplayedMonthMillis = selectedDateMillis,
                yearRange = DatePickerDefaults.YearRange,
                initialDisplayMode = DisplayMode.Picker
            )
        )
    }

    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.large
            )
    ) {
        DatePicker(
            state = internalState,
            showModeToggle = false,
            colors = DatePickerDefaults.colors(
                selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp)
        ) {
            IconButton(onClick = { rechargeable.value += 1 }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.weight(2f)
            ) {
                TextButton(onClick = { setDialog(false) }) {
                    Text(text = "Cancel")
                }
                TextButton(onClick = {
                    setState(internalState)
                    setDialog(false)
                }) {
                    Text(text = "OK")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyDatePickerPreview() {
    val offset: Long = "${ZonedDateTime.now(ZoneId.systemDefault()).offset}".split(":")[0].toLong()

    val internalState by remember {
        mutableStateOf(
            DatePickerState(
                initialSelectedDateMillis = System.currentTimeMillis() - (3600000 * offset),
                initialDisplayedMonthMillis = System.currentTimeMillis() - (3600000 * offset),
                yearRange = DatePickerDefaults.YearRange,
                initialDisplayMode = DisplayMode.Picker
            )
        )
    }
    MyDatePicker(internalState)
}