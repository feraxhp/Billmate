package com.feraxhp.billmate.layauts.tabs.components.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyCardsFunds(components: @Composable () -> Unit) {
    MyCards(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer
                    .copy(alpha = 0.5f),
                shape = MaterialTheme.shapes.large
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = MaterialTheme.shapes.large
            )
            .height(200.dp)
            .padding(10.dp)
    ) {
        components()
    }
}

@Preview
@Composable
fun MyCardsFundsPreview() {
    MyCardsFunds() {

    }
}