package com.feraxhp.billmate.layauts.components.tabs.components.components

import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyCards(
    modifier: Modifier = Modifier,
    components: @Composable () -> Unit = {}
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
    ) {
        components()
    }
}

@Preview
@Composable
fun MyCardsPreview() {
    MyCards() {

    }
}