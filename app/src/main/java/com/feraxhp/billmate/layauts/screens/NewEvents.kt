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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme
import com.feraxhp.billmate.layauts.screens.components.SegmentedButtons

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewEvents() {
    BillmateTheme {
        val labels = listOf("Category Name", "Amount", "Description")
        val (categoryName, setCategoryName) = remember { mutableStateOf("") }
        val (amount, setAmount) = remember { mutableStateOf("") }
        val (description, setDescription) = remember { mutableStateOf("") }
        val values = listOf(categoryName, amount, description)
        val setters = listOf(setCategoryName, setAmount, setDescription)
        val error = remember { mutableStateOf(false) }
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
                                text = "New Funds",
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
                    val (selectedItemValue, getSelectedItem) = remember { mutableStateOf(0) }
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
                                selectedValue = selectedItemValue,
                                onItemClick = getSelectedItem
                            )
                        }
//                        items(labels.size) { position ->
//                            OutlinedTextField(
//                                value = values[position],
//                                onValueChange = {
//                                    setters[position](it)
//                                    error.value = false
//                                },
//                                colors = TextFieldDefaults.outlinedTextFieldColors(
//                                    errorBorderColor = MaterialTheme.colorScheme.error,
//                                ),
//                                label = { Text(labels[position]) },
//                                shape = MaterialTheme.shapes.small,
//                                isError = position == 0 && error.value,
//                                modifier = Modifier
//                                    .fillParentMaxWidth()
//                                    .padding(horizontal = 10.dp)
//                                    .padding(top = 10.dp)
//                            )
//                        }
                    }
                },
                floatingActionButton = {
//                    MyFloatingActionButton(
//                        onClick = {
//                            if (MainActivity.appController.addCategory(
//                                    categoryName, amount, description
//                                )
//                            ) {
//                                error.value = false
//                                viewController.startMainActivity()
//                            } else {
//                                error.value = true
//                            }
//                        },
//                    )
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