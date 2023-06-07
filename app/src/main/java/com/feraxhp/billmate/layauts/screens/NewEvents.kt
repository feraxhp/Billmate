package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.icu.util.TimeZone
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme
import com.feraxhp.billmate.extrendedFuntions.toPointingString
import com.feraxhp.billmate.layauts.screens.components.MyDatePicker
import com.feraxhp.billmate.layauts.screens.components.MyDropDownMenu
import com.feraxhp.billmate.layauts.screens.components.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.MyTimePicker
import com.feraxhp.billmate.layauts.tabs.components.SegmentedButtons
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewEvents() {
    // Texts
    val labels = listOf("Events Name", "Amount", "Description")
    val (eventName, setEventName) = remember { mutableStateOf("") }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }
    val values = listOf(eventName, amount, description)
    val setters = listOf(setEventName, setAmount, setDescription)
    val errorName = remember { mutableStateOf(false) }
    val errorAmount = remember { mutableStateOf(false) }
    val (selectedEventValue, setSelectedEventValue) = remember { mutableStateOf(0) }

    // Dropdown
    // Funds origin
    val (expandedFundsOrigin, setExpandedFundsOrigin) = remember { mutableStateOf(false) }
    val optionsFunds = appController.getAllFundsOnString()
    val (selectedOptionFundOriginText, setSelectedOptionFundOriginText) = remember {
        mutableStateOf(
            optionsFunds[0]
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
            optionsFunds[0]
        )
    }
    // Categories
    val (expandedCategories, setExpandedCategories) = remember { mutableStateOf(false) }
    val optionsCategories = appController.getAllCategoriesOnString()
    val (selectedOptionCategoryText, setSelectedOptionCategoryText) = remember {
        mutableStateOf(
            optionsCategories[0]
        )
    }


    // Clock
    val currentTime = remember { mutableStateOf("") }
    currentTime.value = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    val state = rememberTimePickerState()
    var timeState by remember {
        mutableStateOf(
            TimePickerState(
                initialMinute = currentTime.value.substring(3, 5).toInt(),
                initialHour = currentTime.value.substring(0, 2).toInt(),
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
    val offset: Long = "${ZonedDateTime.now(ZoneId.systemDefault()).offset}".split(":")[0].toLong()
    calendar.timeInMillis =
        System.currentTimeMillis() + (3600000 * offset)
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

    BillmateTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "New Events",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    viewController.startMainActivity()
                                }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "")
                            }
                        }
                    )
                },
                content = { paddingValues ->
                    if (optionsCategories[0] != "" && optionsFunds[0] != "") {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            item {
                                SegmentedButtons(
                                    values = listOf("Expense", "Income", "Transfer"),
                                    selectedValue = selectedEventValue,
                                    setSelectedValue = setSelectedEventValue
                                )
                            }
                            items(labels.size) { position ->
                                if (selectedEventValue == 2 && position == 0) return@items
                                OutlinedTextField(
                                    value = values[position],
                                    onValueChange = {
                                        setters[position](it)
                                        if (position == 0) errorName.value = false
                                        if (position == 1) errorAmount.value = false
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        errorBorderColor = MaterialTheme.colorScheme.error,
                                    ),
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if (position == 1) KeyboardType.Number else KeyboardType.Text),
                                    label = {
                                        if (position == 1 && values[position] != "") {
                                            val text = try {
                                                values[position].toDouble().toPointingString(default = true)
                                            }
                                            catch (e: Exception) {
                                                "Must be a number"
                                            }
                                            Text(text)
                                        } else Text(labels[position])
                                    },
                                    shape = MaterialTheme.shapes.small,
                                    isError = (position == 0 && errorName.value) || (position == 1 && errorAmount.value),
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                        .padding(horizontal = 10.dp)
                                        .padding(top = 10.dp)
                                )
                            }
                            item {
                                MyDropDownMenu(
                                    label = "Fund",
                                    expanded = expandedFundsOrigin,
                                    setExpanded = setExpandedFundsOrigin,
                                    selectedOptionText = selectedOptionFundOriginText,
                                    setSelectedOptionText = setSelectedOptionFundOriginText,
                                    options = optionsFunds,
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                )
                                if (selectedEventValue == 2) {
                                    MyDropDownMenu(
                                        label = "Fund",
                                        expanded = expandedFundsDestination,
                                        setExpanded = setExpandedFundsDestination,
                                        selectedOptionText = selectedOptionFundDestinationText,
                                        setSelectedOptionText = setSelectedOptionFundDestinationText,
                                        options = optionsFunds
                                    )
                                } else {
                                    MyDropDownMenu(
                                        label = "Category",
                                        expanded = expandedCategories,
                                        setExpanded = setExpandedCategories,
                                        selectedOptionText = selectedOptionCategoryText,
                                        setSelectedOptionText = setSelectedOptionCategoryText,
                                        options = optionsCategories
                                    )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    TextButton(onClick = { openTimeDialog.value = true }) {
                                        Text(
                                            text = if (timeState.is24hour) "Time: ${timeState.hour}:${timeState.minute}" else "Time: ${
                                                changeFrom24hto12h(
                                                    timeState.hour,
                                                    timeState.minute
                                                )
                                            }",
                                            modifier = Modifier
                                        )
                                    }
                                    calendar.timeInMillis = dateState.selectedDateMillis!!
                                    TextButton(onClick = { openDateDialog.value = true }) {
                                        Text(
                                            text = "Date: ${calendar[Calendar.DAY_OF_MONTH]}/${calendar[Calendar.MONTH] + 1}/${calendar[Calendar.YEAR]}",
                                            modifier = Modifier
                                        )
                                    }
                                }
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
                                            .fillParentMaxWidth()
                                            .padding(horizontal = 10.dp)
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
                            }
                        }
                    }
                    if (optionsCategories[0] == "") {
                        AlertDialog(
                            onDismissRequest = {
                                viewController.startMainActivity()
                            },
                            title = {
                                Text(text = "No Categories found!")
                            },
                            text = {
                                Text(text = "You must create a category first!")
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        viewController.startCreateNewCategory()
                                    }
                                ) {
                                    Text("Create")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        if (appController.getAllCategoriesOnString() == listOf("")) {
                                            appController.addCategory(
                                                "Default",
                                                "0.0",
                                                "Default Category"
                                            )
                                        }
                                        viewController.startCreateNewEvents()
                                    }
                                ) {
                                    Text("Use default")
                                }
                            }
                        ) // AlertDialog
                    }
                    if (optionsFunds[0] == "") {
                        AlertDialog(
                            onDismissRequest = {
                                viewController.startMainActivity()
                            },
                            title = {
                                Text(text = "No Funds found!")
                            },
                            text = {
                                Text(text = "You must create a fund first!")
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        viewController.startCreateNewFund()
                                    }
                                ) {
                                    Text("Create")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        if (appController.getAllNormalFundsOnString() == listOf("")) {
                                            appController.addFund(
                                                "Default",
                                                "",
                                                "0.0",
                                                "Default Fund"
                                            )
                                        }
                                        viewController.startCreateNewEvents()
                                    }
                                ) {
                                    Text("Use default")
                                }
                            }
                        ) // AlertDialog
                    }

                },
                floatingActionButton = {
                    MyFloatingActionButton(
                        onClick = {
                            when (selectedEventValue) {
                                0, 1 -> {
                                    val response = appController.addEvent(
                                        date = dateState.selectedDateMillis!!,
                                        time = "${timeState.hour}:${timeState.minute}",
                                        category = optionsCategories.indexOf(
                                            selectedOptionCategoryText
                                        ),
                                        fund = optionsFunds.indexOf(
                                            selectedOptionFundDestinationText
                                        ),
                                        name = values[0],
                                        amount = values[1],
                                        description = values[2],
                                        type = selectedEventValue == 1,
                                    )
                                    when (response) {
                                        1 -> {
                                            errorName.value = true
                                        }

                                        2 -> {
                                            errorAmount.value = true
                                        }

                                        else -> {
                                            errorName.value = false
                                            errorAmount.value = false
                                            viewController.startMainActivity()
                                        }
                                    }
                                }

                                2 -> {
                                    val response = appController.addTransfer(
                                        description = values[2],
                                        amount = values[1],
                                        date = dateState.selectedDateMillis!!,
                                        time = "${timeState.hour}:${timeState.minute}",
                                        originFund = optionsFunds.indexOf(
                                            selectedOptionFundOriginText
                                        ),
                                        targetFund = optionsFunds.indexOf(
                                            selectedOptionFundDestinationText
                                        )
                                    )
                                    when (response) {
                                        1 -> {
                                            errorAmount.value = true
                                        }

                                        else -> {
                                            errorAmount.value = false
                                            viewController.startMainActivity()
                                        }
                                    }
                                }
                            }
                        },
                    )
                }
            )
        }
    }
}

fun changeFrom24hto12h(hour: Int, minute: Int): String {
    var returnTime = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} AM"
    if (hour > 12) {
        returnTime =
            "${(hour - 12).toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} PM"
    }
    if (hour == 12) {
        returnTime = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} M"
    }
    if (hour == 0) {
        returnTime =
            "${(12).toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} AM"
    }
    return returnTime
}


@Preview
@Composable
fun NewEventsPreview() {
    NewEvents()
}