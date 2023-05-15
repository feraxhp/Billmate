package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewFund() {
    val (accountName, setAccountName) = remember { mutableStateOf("") }
    val (titularName, setTitularName) = remember { mutableStateOf("") }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }
    val values = listOf<String>(accountName, titularName, amount, description)
    val setters =
        listOf<(String) -> Unit>(setAccountName, setTitularName, setAmount, setDescription)
    val labels = listOf<String>("Account Name", "Titular Name", "Amount", "Description")
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
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(labels.size) { position ->
                            OutlinedTextField(
                                value = values[position],
                                onValueChange = setters[position],
                                label = { Text(labels[position]) },
                                shape = MaterialTheme.shapes.small,
                            )
                        }
                        item {
                            Button(onClick = {
                                if (appController.addFund(
                                        accountName = accountName,
                                        titularName = titularName,
                                        amount = amount,
                                        description = description
                                    )
                                ) {
                                    viewController.startMainActivity()
                                } else {
                                    return@Button
                                }
                            }) {
                                Text(text = "Submit")
                            }
                        }

                    }
                },
            )

        }
    }
}

@Preview
@Composable
fun NewFundPreview() {
    NewFund()
}