package com.feraxhp.billmate.layauts.screens.components.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTimePicker(
    state: TimePickerState,
    setState: (TimePickerState) -> Unit = {},
    setDialog: (Boolean) -> Unit = {}
) {
    val rechargeable = remember {
        mutableStateOf(0)
    }
    val currentTime = remember { mutableStateOf("") }
    currentTime.value = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    val initialMinute: Int
    val initialHour: Int

    if (rechargeable.value != 0) {
        initialMinute = currentTime.value.substring(3, 5).toInt()
        initialHour = currentTime.value.substring(0, 2).toInt()
    } else {
        initialMinute = state.minute
        initialHour = state.hour
    }
    val internalState by remember(key1 = rechargeable.value) {
        mutableStateOf(
            TimePickerState(
                initialMinute = initialMinute,
                initialHour = initialHour,
                is24Hour = state.is24hour
            )
        )
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(24.dp)
            )

    ) {
        Text(
            text = "Select time",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .padding(top = 24.dp, start = 24.dp, bottom = 20.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TimePicker(
                colors = TimePickerDefaults.colors(
                    clockDialColor = MaterialTheme.colorScheme.surfaceColorAtElevation(100.dp),
                    timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                        100.dp
                    )
                ),
                state = internalState,
                modifier = Modifier
                    .padding(horizontal = 20.dp),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(24.dp)
            ) {
                IconButton(
                    onClick = {
                        rechargeable.value += 1
                    }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.weight(2f)
                ) {
                    TextButton(onClick = { setDialog(false) }) {
                        Text(
                            text = "Cancel",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }
                    TextButton(onClick = {
                        setState(internalState)
                        setDialog(false)
                    }) {
                        Text(
                            text = "OK",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimePickerState.copy(
    initialMinute: Int,
    initialHour: Int,
    is24hour: Boolean
): TimePickerState = rememberSaveable(
    saver = TimePickerState.Saver()
) {
    TimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = is24hour,
    )
}

