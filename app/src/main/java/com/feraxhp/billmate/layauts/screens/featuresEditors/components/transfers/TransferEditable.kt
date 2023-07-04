package com.feraxhp.billmate.layauts.screens.featuresEditors.components.transfers

import android.icu.util.Calendar
import android.icu.util.TimeZone
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.extrendedFuntions.dateFormat
import com.feraxhp.billmate.extrendedFuntions.timeFormat
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat
import com.feraxhp.billmate.layauts.screens.featuresCreation.Components.events.MyDatePicker
import com.feraxhp.billmate.layauts.screens.featuresCreation.Components.events.MyDropDownMenu
import com.feraxhp.billmate.layauts.screens.featuresCreation.Components.events.MyTimePicker
import com.feraxhp.billmate.logic_database.database.entities.Transfers
import java.time.ZoneId
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferEditable(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    transfer: Transfers = Transfers(
        amount = 0.0,
        description = "",
        date = 0L,
        time = "23:59",
        origin_fund_id = 0L,
        target_fund_id = 0L
    ),
    isError: (Boolean) -> Unit = {},
    onSave: (Transfers) -> Unit = {},
) {
    val originFund = appController.getFundByID(transfer.origin_fund_id)
    val targetFund = appController.getFundByID(transfer.target_fund_id)

    val (amount, setAmount) = remember {
        mutableStateOf(transfer.amount.toString())
    }
    val (description, setDescription) = remember {
        mutableStateOf(transfer.description)
    }

    val state = rememberTimePickerState()
    var timeState by remember {
        mutableStateOf(
            TimePickerState(
                initialMinute = transfer.time.split(":")[1].toInt(),
                initialHour = transfer.time.split(":")[0].toInt(),
                is24Hour = state.is24hour
            )
        )
    }
    val openTimeDialog = remember { mutableStateOf(false) }

    // Date
    val calendar = Calendar
        .getInstance(
            TimeZone.getTimeZone("UTC${ZonedDateTime.now(ZoneId.systemDefault()).offset}")
        )
    calendar.timeInMillis = transfer.date
    var dateState by remember {
        mutableStateOf(
            DatePickerState(
                initialSelectedDateMillis = calendar.timeInMillis,
                initialDisplayedMonthMillis = calendar.timeInMillis,
                yearRange = 1960..2056,
                initialDisplayMode = DisplayMode.Picker,
            )
        )
    }
    val openDateDialog = remember { mutableStateOf(false) }

    // Dropdown
    // Funds origin
    val (expandedFundsOrigin, setExpandedFundsOrigin) = remember { mutableStateOf(false) }
    val optionsFunds = appController.getAllFundsOnString()
    val (selectedOptionFundOriginText, setSelectedOptionFundOriginText) = remember {
        mutableStateOf(
            "${originFund?.accountName}: ${originFund?.amount?.toMoneyFormat()}"
        )
    }
    //Funds destination
    val (expandedFundsDestination, setExpandedFundsDestination) = remember {
        mutableStateOf(
            false
        )
    }
    val (selectedOptionFundDestinationText, setSelectedOptionFundDestinationText) = remember {
        mutableStateOf(
            "${targetFund?.accountName}: ${targetFund?.amount?.toMoneyFormat()}"
        )
    }


    try {
        onSave(
            transfer.copy(
                amount = amount.toDouble(),
                description = description,
                date = dateState.selectedDateMillis!!,
                time = "${timeState.hour}:${timeState.minute}".timeFormat(),
                origin_fund_id = appController.getAllFunds()[optionsFunds.indexOf(selectedOptionFundOriginText)].id,
                target_fund_id = appController.getAllFunds()[optionsFunds.indexOf(selectedOptionFundDestinationText)].id
            )
        )
        isError(false)
    }catch (_: Exception){
        isError(true)
    }
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(24.dp)
    ) {
        if (openTimeDialog.value) {
            AlertDialog(
                onDismissRequest = {}
            ) {
                MyTimePicker(
                    state = timeState,
                    setState = { timeState = it },
                    setDialog = { openTimeDialog.value = it })
            }
        }
        if (openDateDialog.value) {
            AlertDialog(
                onDismissRequest = {},
                modifier = Modifier
                    .requiredWidth(350.dp)
            ) {
                MyDatePicker(
                    state = dateState,
                    setState = {
                        dateState = it
                        calendar.timeInMillis = it.selectedDateMillis!!
                    },
                    setDialog = { openDateDialog.value = it })
            }
        }
        MyDropDownMenu(
            label = "Origin Fund",
            expanded = expandedFundsOrigin,
            setExpanded = setExpandedFundsOrigin,
            selectedOptionText = selectedOptionFundOriginText,
            setSelectedOptionText = setSelectedOptionFundOriginText,
            options = optionsFunds,
            modifier = Modifier
        )
        MyDropDownMenu(
            label = "Target Fund",
            expanded = expandedFundsDestination,
            setExpanded = setExpandedFundsDestination,
            selectedOptionText = selectedOptionFundDestinationText,
            setSelectedOptionText = setSelectedOptionFundDestinationText,
            options = optionsFunds,
        )

        OutlinedTextField(
            value = amount,
            onValueChange = {
                setAmount(it)
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = OutlinedTextFieldDefaults.colors(
                errorBorderColor = MaterialTheme.colorScheme.error,
            ),
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = {
                if (amount != "") {
                    val text = try {
                        amount.toDouble().toMoneyFormat(default = true)
                    } catch (e: Exception) {
                        "Must be a number"
                    }
                    Text(text)
                } else Text("Amount")
            },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = {
                setDescription(it)
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = OutlinedTextFieldDefaults.colors(
                errorBorderColor = MaterialTheme.colorScheme.error,
            ),
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            label = {
                Text(
                    text = "Event description",
                )
            },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextButton(onClick = { openDateDialog.value = true }) {
                Text(text = dateState.selectedDateMillis!!.dateFormat())
            }
            Text(text = " ~ ", modifier = Modifier.padding(top = 12.dp))
            TextButton(onClick = { openTimeDialog.value = true }) {
                Text(text = "${timeState.hour}:${timeState.minute}".timeFormat(timeState.is24hour))
            }
        }
    }
}

@Preview
@Composable
fun TransferEditablePreview() {
    TransferEditable()
}