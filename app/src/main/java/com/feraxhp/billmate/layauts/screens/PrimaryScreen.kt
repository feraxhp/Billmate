package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
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
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme
import com.feraxhp.billmate.layauts.screens.components.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.MyModalNavigation
import com.feraxhp.billmate.layauts.screens.components.MyNavigationBar
import com.feraxhp.billmate.layauts.screens.components.MyTopBar
import com.feraxhp.billmate.layauts.tabs.FundsTab
import com.feraxhp.billmate.layauts.tabs.HomeTab
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrimaryScreen() {
    val (selectedItemValue, getSelectedItem) = remember { mutableStateOf(0) }
    val titules = listOf("Home", "Funds", "CashFlow", "Categories")
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
                            when (selectedItemValue) {
                                0 -> HomeTab(innerPadding)
                                1 -> FundsTab(innerPadding)
                                2 -> Text(text = "CashFlow")
                                3 -> Text(text = "Categories")
                                else -> {}
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
                                    when (selectedItemValue) {
                                        0 -> viewController.startCreateNewEvents()
                                        1 -> viewController.startCreateNewFund()
                                        2 -> viewController.startCreateNewCashFlow()
                                        3 -> viewController.startCreateNewCategory()
                                        else -> {}
                                    }
                                },
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
    PrimaryScreen()
}