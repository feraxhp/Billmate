package com.feraxhp.billmate.layauts.screens.featuresEditors.components.transfers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.extrendedFuntions.dateFormat
import com.feraxhp.billmate.extrendedFuntions.noDescrition
import com.feraxhp.billmate.extrendedFuntions.timeFormat
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat
import com.feraxhp.billmate.layauts.tabs.components.funds.MyCardsFunds
import com.feraxhp.billmate.logic_database.database.entities.Transfers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferNoEditable(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    transfer: Transfers = Transfers(
        amount = 0.0,
        description = "",
        date = 0L,
        time = "23:59",
        origin_fund_id = 0L,
        target_fund_id = 0L
    ),
) {
    val originFund = MainActivity.appController.getFundByID(transfer.origin_fund_id)
    val targetFund = MainActivity.appController.getFundByID(transfer.target_fund_id)

    val state = rememberTimePickerState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Text(text = "Origin Fund", modifier = Modifier.padding(vertical = 24.dp))
        if (originFund != null) {
            MyCardsFunds(
                accountName = originFund.accountName,
                titularName = originFund.titularName,
                balance = originFund.amount.toMoneyFormat(),
                description = originFund.description,
                type = originFund.type
            )
        }

        Text(text = "Target Fund", modifier = Modifier.padding(vertical = 24.dp))
        if (targetFund != null) {
            MyCardsFunds(
                accountName = targetFund.accountName,
                titularName = targetFund.titularName,
                balance = targetFund.amount.toMoneyFormat(),
                description = targetFund.description,
                type = targetFund.type
            )
        }
        Text(
            text = "Amount: ${transfer.amount.toMoneyFormat()}",
            modifier = Modifier.padding(vertical = 24.dp),
            maxLines = 1,
            textAlign = TextAlign.Center,
            lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
            fontSize = MaterialTheme.typography.titleLarge.fontSize * 1.5f,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
        )
        Text(
            text = "${transfer.date.dateFormat()} ~ ${transfer.time.timeFormat(state.is24hour)}",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 0.dp)
        )
        var maxlines by remember { mutableStateOf(false) }
        Text(
            text = transfer.description.noDescrition(),
            overflow = TextOverflow.Ellipsis,
            maxLines = if (maxlines) Int.MAX_VALUE else 3,
            modifier = Modifier
                .clickable (interactionSource = MutableInteractionSource(), indication = null) {
                    maxlines = !maxlines
                }
        )
    }
}

@Preview
@Composable
fun TransferNoEditablePreview() {
    TransferNoEditable()
}