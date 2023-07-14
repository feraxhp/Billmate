package com.feraxhp.billmate.layauts.screens.featuresEditors.components.funds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat
import com.feraxhp.billmate.layauts.tabs.components.funds.MyCardFund
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.feraxhp.billmate.layauts.tabs.components.components.SegmentedButtons
import com.feraxhp.billmate.logic_database.database.entities.Funds

@Composable
fun FundEditable(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    funds: Funds = Funds(
        id = 0L,
        titularName = "ExampleTitular",
        accountName = "ExampleAccount",
        description = "ExampleDescription",
        amount = 0.0,
        type = 1,
    ),
    isError: (Boolean) -> Unit = {},
    onSave: (Funds) -> Unit = {},
) {
    val (titularName, setTitularName) = remember { mutableStateOf(funds.titularName) }
    val (accountName, setAccountName) = remember { mutableStateOf(funds.accountName) }
    val (description, setDescription) = remember { mutableStateOf(funds.description) }
    val (balance, setBalance) = remember { mutableStateOf(funds.amount.toString()) }

    val values = listOf(accountName, titularName, balance, description)
    val setters = listOf(setAccountName, setTitularName, setBalance, setDescription)

    val (type, setType) = remember { mutableStateOf(funds.type) }

    val errorName = remember { mutableStateOf(false) }
    val errorAmount = remember { mutableStateOf(false) }

    val labels = listOf("Account Name", "Titular Name", "Amount", "Description")

    try {
        onSave(
            funds.copy(
                titularName = titularName,
                accountName = accountName,
                description = description,
                amount = balance.toDouble(),
                type = type
            )
        )
        isError(false)
    } catch (
        _: Exception
    ) {
        isError(true)
    }


    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 24.dp)
    ) {
        MyCardFund(
            accountName = accountName,
            titularName = titularName,
            description = description,
            balance = balance.toDouble().toMoneyFormat(),
            type = type
        )
        SegmentedButtons(
            values = listOf("Normal", "Saves", "Loans"),
            selectedValue = type,
            setSelectedValue = setType,
            modifier = Modifier
        )
        for (position in values.indices) {
            OutlinedTextField(
                value = values[position],
                onValueChange = {
                    setters[position](it)
                    if (position == 0) errorName.value = false
                    if (position == 1) errorAmount.value = false
                },
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    errorBorderColor = MaterialTheme.colorScheme.error,
                ),
                enabled = position != 2,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if (position == 2) KeyboardType.Number else KeyboardType.Text),
                label = {
                    if (position == 2 && values[position] != "") {
                        val text = try {
                            values[position].toDouble()
                                .toMoneyFormat(default = true)
                        } catch (e: Exception) {
                            "Must be a number"
                        }
                        Text(text)
                    } else Text(labels[position])
                },
                shape = MaterialTheme.shapes.small,
                isError = (position == 0 && errorName.value) || (position == 2 && errorAmount.value),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        }
    }
}


@Preview
@Composable
fun FundEditablePreview() {
    FundEditable()
}
