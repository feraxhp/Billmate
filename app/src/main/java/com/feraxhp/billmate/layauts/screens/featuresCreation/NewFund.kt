package com.feraxhp.billmate.layauts.screens.featuresCreation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.layauts.screens.components.primary.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.primary.MyTopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.feraxhp.billmate.layauts.screens.featuresEditors.components.funds.FundEditable
import com.feraxhp.billmate.logic_database.database.entities.Funds

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewFund() {

    var newFund by remember {
        mutableStateOf(
            Funds(
                accountName = "",
                titularName = "",
                description = "",
                amount = 0.0,
                type = 0
            )
        )
    }

    val isErrorName = remember { mutableStateOf(false) }
    val errorAmount = remember { mutableStateOf(false) }


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
            FundEditable(
                paddingValues,
                fund = newFund,
                isError = isErrorName.value,
                setIsError = { isErrorName.value = it },
                isErrorAmount = errorAmount.value,
                setIsErrorAmount = { errorAmount.value = it },
                onSave = { newFund = it },
            )
        },
        floatingActionButton = {
            MyFloatingActionButton(
                text = "Save",
                onClick = {
                    if (isErrorName.value || errorAmount.value) return@MyFloatingActionButton
                    val response = appController.addFund(
                        accountName = newFund.accountName,
                        titularName = newFund.titularName,
                        amount = newFund.amount.toString(),
                        description = newFund.description,
                        type = newFund.type
                    )
                    when (response) {
                        1 -> {
                            isErrorName.value = true
                        }
                        2 -> {
                            errorAmount.value = true
                            isErrorName.value = false
                        }
                        else -> {
                            errorAmount.value = false
                            isErrorName.value = false
                            viewController.finishActivityWithActualize()
                        }
                    }
                },
            )
        }
    )
}
