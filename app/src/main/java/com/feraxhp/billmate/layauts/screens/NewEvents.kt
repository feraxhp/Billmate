package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.icu.util.TimeZone
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePickerDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme
import com.feraxhp.billmate.layauts.screens.components.MyDatePicker
import com.feraxhp.billmate.layauts.screens.components.MyDropDownMenu
import com.feraxhp.billmate.layauts.screens.components.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.MyTimePicker
import com.feraxhp.billmate.layauts.screens.components.SegmentedButtons
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
    val error = remember { mutableStateOf(false) }
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
    val calendar =
        Calendar.getInstance(TimeZone.getTimeZone("UTC${ZonedDateTime.now(ZoneId.systemDefault()).offset}"))
    calendar.timeInMillis = System.currentTimeMillis()
    var dateState by remember {
        mutableStateOf(
            DatePickerState(
                initialSelectedDateMillis = calendar.timeInMillis,
                initialDisplayedMonthMillis = calendar.timeInMillis,
                yearRange = DatePickerDefaults.YearRange,
                initialDisplayMode = DisplayMode.Picker
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
                                    buttonNames = listOf("Expense", "Income", "Transfer"),
                                    selectedValue = selectedEventValue,
                                    onItemClick = setSelectedEventValue
                                )
                            }
                            items(labels.size) { position ->
                                OutlinedTextField(
                                    value = values[position],
                                    onValueChange = {
                                        setters[position](it)
                                        error.value = false
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        errorBorderColor = MaterialTheme.colorScheme.error,
                                    ),
                                    label = { Text(labels[position]) },
                                    shape = MaterialTheme.shapes.small,
                                    isError = position == 0 && error.value,
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
                                TextButton(onClick = { openTimeDialog.value = true }) {
                                    Text(
                                        text = "Time ${timeState.hour}:${timeState.minute}",
                                        modifier = Modifier
                                    )
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
                                calendar.timeInMillis = dateState.selectedDateMillis!!
                                TextButton(onClick = { openDateDialog.value = true }) {
                                    Text(
                                        text = "Date: ${calendar[Calendar.DAY_OF_MONTH]}/${calendar[Calendar.MONTH] + 1}/${calendar[Calendar.YEAR]}",
                                        modifier = Modifier
                                    )
                                }
                                if (openDateDialog.value) {
                                    AlertDialog(
                                        onDismissRequest = {}
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
                            onDismissRequest = {},
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
                            onDismissRequest = {},
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
                                        if (appController.getAllFundsOnString() == listOf("")) {
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
                                0 -> {

                                }

                                1 -> {

                                }

                                2 -> {

                                }
                            }
                        },
                    )
                }
            )
        }
    }
}


@Preview
@Composable
fun NewEventsPreview() {
    NewEvents()
}