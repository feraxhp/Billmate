package com.feraxhp.billmate.layauts.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyFloatingActionButton(onClick: () -> Unit = {}) {
    FloatingActionButton(onClick = onClick, modifier = Modifier.size(60.dp)) {
        Icon(Icons.Filled.Add, "")
    }
}