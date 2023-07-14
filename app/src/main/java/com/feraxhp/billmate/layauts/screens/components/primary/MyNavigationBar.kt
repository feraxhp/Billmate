package com.feraxhp.billmate.layauts.screens.components.primary

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.feraxhp.billmate.ui.theme.Elevations

val items = listOf("Home", "Funds", "CashFlow", "Categories")
private val icons =
    listOf(Icons.Outlined.Home, Icons.Outlined.Person, Icons.Outlined.Lock, Icons.Outlined.Face)
private val iconsSelected =
    listOf(Icons.Filled.Home, Icons.Filled.Person, Icons.Filled.Lock, Icons.Filled.Face)

@Composable
fun MyNavigationBar(selectedItem: Int, onItemClick: (Int) -> Unit) {
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(Elevations.level2),
            ) {
        items.forEachIndexed { index, item ->
            val selected = selectedItem == index
            NavigationBarItem(
                icon = {
                    Icon(
                        if (!selected) icons[index] else iconsSelected[index],
                        contentDescription = item
                    )
                },
                label = {
                    Text(
                        item,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = if (!selected) null else FontWeight.Bold
                    )
                },
                selected = selected,
                onClick = { onItemClick(index) }
            )
        }
    }
}
