package com.feraxhp.billmate.layauts.tabs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.controllers.dependencies.Activities
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat
import com.feraxhp.billmate.layauts.tabs.components.funds.MyCardFund
import com.feraxhp.billmate.layauts.tabs.components.components.MyAlertDialog

@SuppressLint("RememberReturnType")
@Composable
fun FundsTab(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    hasNoItems: (Boolean) -> Unit = {},
    setScrollState: (Int) -> Unit = {}
) {

    var list by remember { mutableStateOf(appController.getAllFunds()) }
    val lazyListState = rememberLazyListState()
    val scrollValue by remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    setScrollState(scrollValue)

    if (list.isEmpty()) {
        hasNoItems(true)
    }else {
        hasNoItems(false)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
        ,
        contentPadding = innerPadding,
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState,
    ) {
        items(list.toMutableList()) { fund ->
            var showDialog by remember { mutableStateOf(false) }
            MyCardFund(
                accountName = fund.accountName,
                balance = fund.amount.toMoneyFormat(),
                titularName = fund.titularName,
                description = fund.description,
                type = fund.type,
                onClick = {
                    showDialog = true
                },
                onBodyClick = {
                    viewController.fund2Edit = fund
                    viewController.startActivity(Activities.editFunds)
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            MyAlertDialog(
                openState = showDialog,
                setOpenState = { showDialog = it },
                title = "Delete this fund?",
                message = "If you delete this fund, you will not be able to recover it, and it will delete all transactions related to this fund.",
                onConfirm = {
                    appController.removeFund(fund)
                    list = list
                        .toMutableList()
                        .apply {
                            remove(fund)
                        }
                }
            )
        }
    }
}

@Preview
@Composable
fun FundsTabPreview() {
    FundsTab()
}