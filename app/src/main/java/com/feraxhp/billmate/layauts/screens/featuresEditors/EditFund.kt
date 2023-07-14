package com.feraxhp.billmate.layauts.screens.featuresEditors

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.layauts.screens.components.primary.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.primary.MyTopAppBar
import com.feraxhp.billmate.layauts.screens.featuresEditors.components.funds.FundEditable
import com.feraxhp.billmate.layauts.screens.featuresEditors.components.funds.FundNoEditable
import com.feraxhp.billmate.logic_database.database.entities.Funds


@Composable
fun EditFund(
    fund: Funds = Funds(
        id = 10L,
        titularName = "ExampleTitular",
        accountName = "ExampleAccount",
        description = "ExampleDescription",
        amount = 0.0,
        type = 1,
    )
) {
    var isEditable by remember { mutableStateOf(false) }

    var isError by remember { mutableStateOf(false) }
    var editedFund by remember {
        mutableStateOf(
            Funds(
                titularName = "ExampleTitular",
                accountName = "ExampleAccount",
                description = "ExampleDescription",
                amount = 0.0,
                type = 1
            )
        )
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                text = "Edit Fund",
                navigationIcon = Icons.Filled.ArrowBack,
                NavigationActionComposable = {
                    viewController.finishActivity()
                },
                searchActionComposable = {
                    isEditable = !isEditable
                },
                searchIcon = if (isEditable) Icons.Filled.Close else Icons.Filled.Edit,
            )
        },
        content = {
            when (isEditable) {
                true -> FundEditable(it, fund, isError = { isError = it}, onSave = { editedFund = it })
                false -> FundNoEditable(it, fund)
            }
        },
        floatingActionButton = {
            when (isEditable) {
                true -> MyFloatingActionButton(
                    text = "Save",
                    withIcon = false,
                    onClick = {
                        if (!isError && editedFund != fund) {
                            val response = MainActivity.appController.editFund(
                                editedFund
                            )
                            if (response) {
                                viewController.finishActivityWithActualize()
                            }
                        } else if (editedFund == fund) {
                            isEditable = false
                        }
                    }
                )
                false -> {}
            }
        }
    )
}

@Preview
@Composable
fun EditFundPreview() {
    EditFund()
}