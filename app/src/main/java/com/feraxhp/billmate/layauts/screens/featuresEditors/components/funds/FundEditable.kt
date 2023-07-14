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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat
import com.feraxhp.billmate.layauts.tabs.components.funds.MyCardFund
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.extrendedFuntions.noDescrition
import com.feraxhp.billmate.layauts.tabs.components.components.SegmentedButtons
import com.feraxhp.billmate.logic_database.database.entities.Funds

@Composable
fun FundEditable(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    fund: Funds = Funds(
        id = 0L,
        titularName = "ExampleTitular",
        accountName = "ExampleAccount",
        description = "ExampleDescription",
        amount = 0.0,
        type = 1,
    ),
    isError: Boolean = false,
    setIsError: (Boolean) -> Unit = {},
    isErrorAmount: Boolean = false,
    setIsErrorAmount: ((Boolean) -> Unit)? = null,
    onSave: (Funds) -> Unit = {},
) {
    val (titularName, setTitularName) = remember { mutableStateOf(fund.titularName) }
    val (accountName, setAccountName) = remember { mutableStateOf(fund.accountName) }
    val (description, setDescription) = remember { mutableStateOf(fund.description) }
    val (balance, setBalance) = remember { mutableStateOf(fund.amount.toString()) }

    val values = listOf(accountName, titularName, balance, description)
    val setters = listOf(setAccountName, setTitularName, setBalance, setDescription)

    val (type, setType) = remember { mutableStateOf(fund.type) }

    var wasOnFocus by remember { mutableStateOf(false) }

    if (balance == "") {
        setBalance("0.0")
        setIsErrorAmount!!(false)
    }
    if (accountName != "") {
        setIsError(false)
    }

    val labels = listOf("Account Name", "Titular Name", "Balance", "Description")

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 24.dp)
    ) {
        MyCardFund(
            accountName = if (accountName == "") "Account Name" else accountName,
            titularName = if (titularName == "") appController.user.getName()!! else titularName,
            description = description.noDescrition(),
            balance = try { balance.toDouble().toMoneyFormat()} catch (e: Exception) {"Must be a number"},
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
                value = if (values[position] == 0.0.toString()) "" else values[position],
                onValueChange = {
                    setters[position](it)
                    if (position == 0) {
                        setIsError(false)
                        wasOnFocus = true
                    }
                    if (position == 2) setIsErrorAmount!!(false)
                },
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    errorBorderColor = MaterialTheme.colorScheme.error,
                ),

                enabled = position != 2 || setIsErrorAmount != null,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if (position == 2) KeyboardType.Number else KeyboardType.Text),
                label = {
                    if (values[position] == 0.0.toString()) Text(text = labels[position])
                    else if (position == 2 && values[position] != "") {
                        val text = try {
                            values[position]
                                .toDouble()
                                .toMoneyFormat(default = true)
                        } catch (e: Exception) {
                            setIsErrorAmount!!(true)
                            "Must be a number"
                        }
                        Text(text)
                    }
                    else if (values[position] == "") Text(labels[position])
                    else Text(values[position])
                },
                shape = MaterialTheme.shapes.small,
                isError = (position == 0 && isError) || (position == 2 && isErrorAmount),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        }
    }

    try {
        if (accountName == "" && wasOnFocus) {
            setIsError(true)
            throw Exception()
        }
        if (isErrorAmount || isError) {
            throw Exception()
        }
        onSave(
            fund.copy(
                titularName = titularName,
                accountName = accountName,
                description = description,
                amount = balance.toDouble(),
                type = type
            )
        )
    } catch (
        _: Exception
    ) {}
}


@Preview
@Composable
fun FundEditablePreview() {
    FundEditable()
}
