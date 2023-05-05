package com.feraxhp.billmate.layauts.screens.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyFloatingActionButton(onClick: () -> Unit = {}) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.surfaceTint,
        onClick = onClick,
        modifier = Modifier
            .size(60.dp)
    ) {
        Icon(Icons.Filled.Add, "", tint = MaterialTheme.colorScheme.inverseOnSurface)
    }
}