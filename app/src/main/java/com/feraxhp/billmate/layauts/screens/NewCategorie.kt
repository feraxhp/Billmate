package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme
import com.feraxhp.billmate.layauts.screens.components.MyFloatingActionButton

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewCategory() {
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
                                text = "New Category",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    MainActivity.viewController.startMainActivity()
                                }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "")
                            }
                        }
                    )
                },
                content = { paddingValues ->
//                    LazyColumn(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Top,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(paddingValues)
//                    ) {
//                        items(labels.size) { position ->
//                            OutlinedTextField(
//                                value = values[position],
//                                onValueChange = {
//                                    setters[position] (it)
//                                    error.value = false },
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
//                    }
                },
                floatingActionButton = {
                    MyFloatingActionButton(
                        onClick = {
//                            if (MainActivity.appController.addFund(
////                                    accountName = accountName,
////                                    titularName = titularName,
////                                    amount = amount,
////                                    description = description
//                                )
//                            ) {
//                                error.value = false
//                                MainActivity.viewController.startMainActivity()
//                            } else {
//                                error.value = true
//                            }
                        },
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun NewCategoryPreview() {
    NewCategory()
}