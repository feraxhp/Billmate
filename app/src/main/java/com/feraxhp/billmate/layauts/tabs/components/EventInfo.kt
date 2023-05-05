package com.feraxhp.billmate.layauts.tabs.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.layauts.tabs.components.components.MyCards

@Composable
fun EventInfo() {
    MyCards(
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 15.dp)
            .padding(top = 15.dp),
    ) {

    }
}

@Preview
@Composable
fun EventInfoPreview() {
    EventInfo()
}