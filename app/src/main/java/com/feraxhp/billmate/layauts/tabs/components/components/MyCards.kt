package com.feraxhp.billmate.layauts.tabs.components.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyCards(
    modifier: Modifier = Modifier.size(100.dp),
    components: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 7.dp, shape = MaterialTheme.shapes.large,
            )
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.large,
            )
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