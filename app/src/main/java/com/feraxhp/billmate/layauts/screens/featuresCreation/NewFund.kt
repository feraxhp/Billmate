package com.feraxhp.billmate.layauts.screens.featuresCreation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat
import com.feraxhp.billmate.layauts.screens.components.primary.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.primary.MyTopAppBar
import com.feraxhp.billmate.layauts.tabs.components.components.SegmentedButtons

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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(
                text = "New Fund",
                NavigationActionComposable = { viewController.finishActivity() },
                navigationIcon = Icons.Filled.ArrowBack,
                searchIcon = null
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
                            maxLines = 1,
                            colors = OutlinedTextFieldDefaults.colors(
                                errorBorderColor = MaterialTheme.colorScheme.error,
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if (position == 2) KeyboardType.Number else KeyboardType.Text),
                            label = {
                                if (position == 2 && values[position] != "") {
                                    val text = try {
                                        values[position].toDouble()
                                            .toMoneyFormat(default = true)
                                    } catch (e: Exception) {
                                        "Must be a number"
                                    }
                                    Text(text)
                                } else Text(labels[position])
                            },
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
                text = "Save",
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
                            viewController.finishActivityWithActualize()
                        }
                    }
                },
            )
        }
    )
}
