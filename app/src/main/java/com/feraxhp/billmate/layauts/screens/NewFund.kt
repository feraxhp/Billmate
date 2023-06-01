package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme
import com.feraxhp.billmate.layauts.screens.components.MyFloatingActionButton
import com.feraxhp.billmate.layauts.tabs.components.SegmentedButtons

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewFund() {
    val (accountName, setAccountName) = remember { mutableStateOf("") }
    val (titularName, setTitularName) = remember { mutableStateOf("") }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }
    val errorName = remember { mutableStateOf(false) }
    val errorAmount = remember { mutableStateOf(false) }
    val values = listOf(accountName, titularName, amount, description)
    val setters = listOf(setAccountName, setTitularName, setAmount, setDescription)
    val labels = listOf("Account Name", "Titular Name", "Amount", "Description")
    val (selectedType, setSelectedType) = remember { mutableStateOf(0) }
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
                                text = "New Fund",
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
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                    ) {
                        SegmentedButtons(
                            values = listOf("Normal", "Saves", "Loans"),
                            selectedValue = selectedType,
                            setSelectedValue = setSelectedType
                        )
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(labels.size) { position ->
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
                                    label = { Text(labels[position]) },
                                    shape = MaterialTheme.shapes.small,
                                    isError = (position == 0 && errorName.value) || (position == 2 && errorAmount.value),
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                        .padding(horizontal = 10.dp)
                                        .padding(top = 10.dp)
                                )
                            }
                        }
                    }
                },
                floatingActionButton = {
                    MyFloatingActionButton(
                        onClick = {
                            val response = appController.addFund(
                                accountName = accountName,
                                titularName = titularName,
                                amount = amount,
                                description = description,
                                type = selectedType
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
                        },
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun NewFundPreview() {
    NewFund()
}