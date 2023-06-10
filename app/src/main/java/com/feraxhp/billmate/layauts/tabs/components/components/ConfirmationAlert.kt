package com.feraxhp.billmate.layauts.tabs.components.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ConfirmationAlert(
    openState: Boolean = true,
    setOpenState: (Boolean) -> Unit = {},
    title: String = "Are you sure?",
    text: String = "here is the text",
    onConfirm: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    if (openState) {
        AlertDialog(
            onDismissRequest = {
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
                        setOpenState(false)
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
fun ConfirmationAlertPreview() {
    ConfirmationAlert()
}