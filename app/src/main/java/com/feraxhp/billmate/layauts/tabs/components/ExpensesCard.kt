package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.layauts.tabs.components.components.MyCards

@Composable
fun ExpensesCard() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(end = 15.dp)
            .height(100.dp)
    ) {
        val text: List<String> = listOf("Expenses", "Income")
        text.forEach {
            MyCards(
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .height(intrinsicSize = IntrinsicSize.Min)
            ) {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(start = 15.dp, top = 15.dp)
                )
            }
        }

    }
}

@Preview
@Composable
fun ExpensesCardPreview() {
    ExpensesCard()
}