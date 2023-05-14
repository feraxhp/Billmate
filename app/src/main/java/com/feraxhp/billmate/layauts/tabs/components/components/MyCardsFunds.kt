package com.feraxhp.billmate.layauts.tabs.components.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyCardsFunds(modifier: Modifier = Modifier, components: @Composable () -> Unit) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = CardDefaults.shape
            )
            .alpha(1f)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
                    .copy(alpha = 0.5f),
                shape = CardDefaults.shape
            )
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