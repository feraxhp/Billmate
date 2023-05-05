package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.layauts.tabs.components.components.MyCards


@Composable
fun BalanceCard() {
    MyCards(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(150.dp),
    ) {
        Text(
            text = "Home",
            modifier = Modifier
                .padding(15.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun BalanceCardPreview() {
    BalanceCard()
}