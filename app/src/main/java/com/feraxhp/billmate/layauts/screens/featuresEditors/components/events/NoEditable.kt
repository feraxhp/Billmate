package com.feraxhp.billmate.layauts.screens.featuresEditors.components.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.extrendedFuntions.dateFormat
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat
import com.feraxhp.billmate.extrendedFuntions.noDescrition
import com.feraxhp.billmate.extrendedFuntions.timeFormat
import com.feraxhp.billmate.layauts.tabs.components.categories.CategoriesMessage
import com.feraxhp.billmate.layauts.tabs.components.funds.MyCardsFunds
import com.feraxhp.billmate.logic_database.database.entities.Events

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoEditable(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    Event: Events = Events(
        name = "ExampleName",
        amount = 111000.0,
        description = "ExampleDescription",
        date = 0L,
        time = "12:30",
        type = false,
        fund_id = 0L,
        category_id = 0L
    )
) {
    val spacerPadding = 24.dp
    val state = rememberTimePickerState()
    val color = if (Event.type) Color(0XFF008C37) else Color(0xFFff0000)

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = Event.name,
            textAlign = TextAlign.Center,
            lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
            fontSize = MaterialTheme.typography.titleLarge.fontSize * 1.5f,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
            color = color,
            modifier = Modifier
        )
        Text(
            text = "${Event.date.dateFormat()} ~ ${Event.time.timeFormat(state.is24hour)}",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = spacerPadding)
        )
        Spacer(modifier = Modifier.height(spacerPadding))
        Text(
            text = "Amount: ${Event.amount.toMoneyFormat()}",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(spacerPadding))
        Text(
            text = Event.description.noDescrition(),
        )
        Spacer(modifier = Modifier.height(spacerPadding))
        val fund = appController.getFundByID(Event.fund_id)
        if (fund != null) {
            MyCardsFunds(
                accountName = fund.accountName,
                titularName = fund.titularName,
                balance = fund.amount.toMoneyFormat(),
                description = fund.description
            )
        } else {
            MyCardsFunds()
        }
        Spacer(modifier = Modifier.height(spacerPadding))
        val category = appController.getCategoryByID(Event.category_id)
        if (category != null) {
            CategoriesMessage(
                name = category.name,
                amount = category.amount,
            )
        } else {
            CategoriesMessage()
        }
    }
}

//@Composable
//fun MyDivider(spacerPadding: Dp, dividerMultiplayer: Float = 1f, spacerMultiplayer: Float = 1f) {
//    Divider(modifier = Modifier.padding(top = spacerPadding * dividerMultiplayer))
//    Spacer(modifier = Modifier.padding(spacerPadding * spacerMultiplayer))
//}
//@Preview
//@Composable
//fun NoneditablePreview() {
//    NoEditable()
//}
