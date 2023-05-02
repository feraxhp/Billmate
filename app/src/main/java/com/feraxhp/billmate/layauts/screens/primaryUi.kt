package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.ui.theme.BillmateTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.feraxhp.billmate.layauts.TopBar
import com.feraxhp.billmate.layauts.Bars.MyModalNavigation
import com.feraxhp.billmate.layauts.Bars.MyNavigationBar


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrimaryUi() {
    val (selectedItemValue, getSelectedItem) = remember { mutableStateOf(0) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BillmateTheme {
        // A surface container using the 'background' color from the theme
        print("PrimaryUi")

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
                            val text: String
                            text = when (selectedItemValue) {
                                0 -> "home"
                                1 -> "accounts"
                                2 -> "debts"
                                3 -> "overview"
                                else -> "home"
                            }
                            TopBar(text = text,
                                navigationAction = {
                                scope.launch { drawerState.open() }
                            }, searchAction = {

                            })
                        },
                        content = {
                            LazyColumn {
                                item {
                                    for (i in 1..100) {

                                        Text(text = "Home $i")
                                    }
                                }
                            }
//                          when (selectedItemValue) {
//                              0 -> Text(text = "home")
//                              1 -> Text(text = "accounts")
//                              2 -> Text(text = "debts")
//                              3 -> Text(text = "overview")
//                              else -> Text(text = "home")
//                          }
                        },
                        bottomBar = {
                            MyNavigationBar(
                                selectedItem = selectedItemValue,
                                onItemClick = getSelectedItem
                            )
                        },
                    )
                })

        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
    PrimaryUi()
}