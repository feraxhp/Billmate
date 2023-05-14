package com.feraxhp.billmate.layauts.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.layauts.tabs.components.components.MyCardsFunds

@Composable
fun FundsTab(innerPadding: PaddingValues = PaddingValues(0.dp)) {
    val list by remember { mutableStateOf(appController.getAllFunds()) }
    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(list.toMutableList()) {
            MyCardsFunds {
                IconButton(onClick = {
                    appController.removeFund(it)
                    list.toMutableList().remove(it)
                }) {
                    Text(text = "X")
                }
                Text(text = it.name)
            }
        }

    }
}

@Preview
@Composable
fun FundsTabPreview() {
    FundsTab()
}