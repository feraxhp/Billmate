package com.feraxhp.billmate.layauts.screens.components

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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePicker(
    state: DatePickerState = DatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        initialDisplayedMonthMillis = System.currentTimeMillis(),
        yearRange = 1960..2023,
        initialDisplayMode = DisplayMode.Picker,
    ),
    setState: (DatePickerState) -> Unit = {},
    setDialog: (Boolean) -> Unit = {}
) {
    val rechargeable = remember {
        mutableStateOf(0)
    }

    val selectedDateMillis =
        if (rechargeable.value != 0) System.currentTimeMillis() else state.selectedDateMillis

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

//@Preview(uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun MyDatePickerPreview() {
//    MyDatePicker()
//}