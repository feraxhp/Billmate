package com.feraxhp.billmate.layauts.tabs.components.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
@Composable
fun MyAlertDialog(
    openState: Boolean = true,
    setOpenState: (Boolean) -> Unit = {},
    title: String,
    text: String,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
    dismissText: String
) {
    if (openState) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
                setOpenState(false)
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = text)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm()
                        setOpenState(false)
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onCancel()
                        setOpenState(false)
                    }
                ) {
                    Text(dismissText)
                }
            }
        )
    }
}
@Composable
fun MyAlertDialog(
    title: String = "Are you sure?",
    message: String = "here is the text",
    openState: Boolean = true,
    setOpenState: (Boolean) -> Unit = {},
    onDismissRequestComposable: @Composable () -> Unit = {},
    onConfirm: @Composable () -> Unit = {},
    onCancel: @Composable () -> Unit = {},
    dismissText: String = "Cancel",
) {
    var isRequested by remember { mutableStateOf(false) }
    var isCancelled by remember { mutableStateOf(false) }
    var isConfirmed by remember { mutableStateOf(false) }

    if (isRequested) onDismissRequestComposable(); isRequested = false
    if (isCancelled) onCancel(); isCancelled = false
    if (isConfirmed) onConfirm(); isConfirmed = false

    MyAlertDialog(
        onDismissRequest = { isRequested = true },
        title = title,
        text = message,
        openState = openState,
        setOpenState = setOpenState,
        onConfirm = { isConfirmed = true },
        onCancel = { isCancelled = true },
        dismissText = dismissText
    )
}

@Preview
@Composable
fun ConfirmationAlertPreview() {
    MyAlertDialog()
}