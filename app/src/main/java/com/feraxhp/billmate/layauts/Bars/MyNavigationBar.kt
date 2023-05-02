package com.feraxhp.billmate.layauts.Bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

val items = listOf("Home", "Accounts", "Debs", "Overview")
val icons =
    listOf(Icons.Outlined.Home, Icons.Outlined.Person, Icons.Outlined.Lock, Icons.Outlined.Face)

@Composable
fun MyNavigationBar(selectedItem: Int, onItemClick: (Int) -> Unit) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { onItemClick(index) }
            )
        }
    }
}
