package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme
import com.feraxhp.billmate.layauts.screens.components.MyDropDownMenu
import com.feraxhp.billmate.layauts.screens.components.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.SegmentedButtons

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewEvents() {
    BillmateTheme {
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
                                colors = TextFieldDefaults.outlinedTextFieldColors(
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
                        }
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