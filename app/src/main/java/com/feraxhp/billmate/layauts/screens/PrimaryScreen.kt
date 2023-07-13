package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme
import com.feraxhp.billmate.controllers.dependencies.Activities
import com.feraxhp.billmate.layauts.screens.components.primary.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.primary.MyModalNavigation
import com.feraxhp.billmate.layauts.screens.components.primary.MyNavigationBar
import com.feraxhp.billmate.layauts.screens.components.primary.MyTopAppBar
import com.feraxhp.billmate.layauts.tabs.CashFlowTab
import com.feraxhp.billmate.layauts.tabs.CategoryTab
import com.feraxhp.billmate.layauts.tabs.FundsTab
import com.feraxhp.billmate.layauts.tabs.HomeTab
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrimaryScreen() {


    var scrollState by remember { mutableStateOf(0) }
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }
    var cashFlowTitle by remember(key1 = selectedTab) { mutableStateOf("") }
    val titles = listOf("Home", "Funds", if (scrollState == 0)"Cash Flow " else cashFlowTitle, "Categories")
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BillmateTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyModalNavigation(
                selectedItem = selectedTab,
                onItemClick = setSelectedTab,
                drawerState = drawerState,
                scope = scope
            ) {
                Scaffold(
                    topBar = {
                        MyTopAppBar(
                            text = titles[selectedTab],
                            navigationAction = {
                                scope.launch { drawerState.open() }
                            },
                            actionIconAction = {

                            },
                            scrollState = scrollState
                        )

                    },
                    content = { innerPadding ->
                        when (selectedTab) {
                            0 -> HomeTab(innerPadding) { scrollState = it }
                            1 -> FundsTab(innerPadding) { scrollState = it }
                            2 -> CashFlowTab(innerPadding, setScrollState = { scrollState = it }, setTitle = {cashFlowTitle = it})
                            3 -> CategoryTab(innerPadding) { scrollState = it }
                            else -> { }
                        }
                    },
                    bottomBar = {
                        MyNavigationBar(
                            selectedItem = selectedTab,
                            onItemClick = setSelectedTab
                        )
                    },
                    floatingActionButton = {
                        MyFloatingActionButton(
                            onClick = {
                                when (selectedTab) {
                                    0 -> viewController.startActivity(Activities.createNewEvents)
                                    1 -> viewController.startActivity(Activities.createNewFund)
                                    2 -> viewController.startActivity(Activities.createNewEvents)
                                    3 -> viewController.startActivity(Activities.createNewCategory)
                                    else -> {}
                                }
                            }
                        )
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
    PrimaryScreen()
}