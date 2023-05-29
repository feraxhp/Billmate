package com.feraxhp.billmate.layauts.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTimePicker(
    state: TimePickerState,
    setState: (TimePickerState) -> Unit = {},
    setDialog: (Boolean) -> Unit = {}
) {
    var internalState = rememberTimePickerState()

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
                IconButton(onClick = { }) {
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
                    Text(
                        text = "Cancel",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                setDialog(false)
                            }
                    )
                    Text(
                        text = "OK",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                setState(internalState)
                                setDialog(false)
                            }
                    )
                }
            }
        }
    }
}