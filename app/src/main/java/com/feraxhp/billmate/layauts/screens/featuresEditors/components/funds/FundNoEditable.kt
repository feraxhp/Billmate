package com.feraxhp.billmate.layauts.screens.featuresEditors.components.funds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.layauts.tabs.CashFlowTab
import com.feraxhp.billmate.layauts.tabs.components.funds.MyCardFund
import com.feraxhp.billmate.logic_database.database.entities.Funds
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat

@Composable
fun FundNoEditable(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    funds: Funds = Funds(
        id = 0L,
        titularName = "ExampleTitular",
        accountName = "ExampleAccount",
        description = "ExampleDescription",
        amount = 0.0,
        type = 1,
    )
) {
    val titularName by remember { mutableStateOf(funds.titularName) }
    val accountName by remember { mutableStateOf(funds.accountName) }
    val description by remember { mutableStateOf(funds.description) }
    val balance by remember { mutableStateOf(funds.amount) }
    val type by remember { mutableStateOf(funds.type) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 24.dp)
    ) {
        MyCardFund(
            accountName = accountName,
            titularName = titularName,
            description = description,
            balance = balance.toMoneyFormat(),
            type = type
        )
        CashFlowTab(
            fundId = funds.id,
            horizontalPadding = 0.dp
        )
    }
}

@Preview
@Composable
fun FundNoEditablePreview() {
    FundNoEditable()
}