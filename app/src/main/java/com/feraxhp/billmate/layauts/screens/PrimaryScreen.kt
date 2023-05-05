package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme
import com.feraxhp.billmate.layauts.screens.components.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.MyModalNavigation
import com.feraxhp.billmate.layauts.screens.components.MyNavigationBar
import com.feraxhp.billmate.layauts.screens.components.MyTopBar
import com.feraxhp.billmate.layauts.tabs.HomeTab
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrimaryUi() {
    val (selectedItemValue, getSelectedItem) = remember { mutableStateOf(0) }
    val titules = listOf("Home", "Accounts", "Debts", "Overview")
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BillmateTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyModalNavigation(
                selectedItem = selectedItemValue,
                onItemClick = getSelectedItem,
                drawerState = drawerState,
                scope = scope,
                content = {
                    Scaffold(
                        topBar = {
                            MyTopBar(
                                text = titules[selectedItemValue],
                                navigationAction = {
                                    scope.launch { drawerState.open() }
                                },
                                searchAction = {

                                },
                                opacity = 1f,
                            )

                        },
                        content = { innerPadding ->

                            LazyColumn(
                                contentPadding = innerPadding,
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier
                            ) {
                                item {
                                    when (selectedItemValue) {
                                        0 -> HomeTab()
                                        1 -> Text(text = "accounts")
                                        2 -> Text(text = "debts")
                                        3 -> Text(text = "overview")
                                        else -> Text(text = "home")
                                    }
                                }
                            }
                        },
                        bottomBar = {
                            MyNavigationBar(
                                selectedItem = selectedItemValue,
                                onItemClick = getSelectedItem
                            )
                        },
                        floatingActionButton = {
                            MyFloatingActionButton(
                                onClick = {

                                }
                            )
                        }
                    )
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
    PrimaryUi()
}