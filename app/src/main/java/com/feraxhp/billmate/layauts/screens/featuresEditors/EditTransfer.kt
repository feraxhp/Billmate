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
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.layauts.screens.components.primary.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.primary.MyTopAppBar
import com.feraxhp.billmate.layauts.screens.featuresEditors.components.transfers.TransferEditable
import com.feraxhp.billmate.layauts.screens.featuresEditors.components.transfers.TransferNoEditable
import com.feraxhp.billmate.logic_database.database.entities.Transfers

@Composable
fun EditTransfer(
    transfer: Transfers = Transfers(
        amount = 0.0,
        description = "",
        date = 0L,
        time = "23:59",
        origin_fund_id = 0L,
        target_fund_id = 0L
    )
) {

    var isEditable by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    var editedTransfer by remember {
        mutableStateOf(
            Transfers(
                amount = 0.0,
                description = "",
                date = 0L,
                time = "23:59",
                origin_fund_id = 0L,
                target_fund_id = 0L
            )
        )
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                text = "Transfer",
                NavigationActionComposable = {
                    if (isEditable) isEditable = false else viewController.finishActivity()
                },
                navigationIcon = Icons.Filled.ArrowBack,
                searchActionComposable = { isEditable = !isEditable },
                searchIcon = if (isEditable) Icons.Filled.Close else Icons.Filled.Edit,
            )
        },
        content = {
            when (isEditable) {
                true -> TransferEditable(
                    paddingValues = it,
                    transfer = transfer,
                    isError = { isError = it },
                    onSave = { editedTransfer = it })

                false -> TransferNoEditable(paddingValues = it, transfer = transfer)
            }
        },
        floatingActionButton = {
            when (isEditable) {
                true -> MyFloatingActionButton(
                    text = "Save",
                    withIcon = false,
                    onClick = {
                        if (!isError && editedTransfer != transfer) {
                            val response = appController.editTransfer(
                                editedTransfer
                            )
                            if (response) {
                                viewController.finishActivityWithActualize()
                            }
                        } else if (editedTransfer == transfer) {
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
fun EditTransferPreview() {
    EditTransfer()
}