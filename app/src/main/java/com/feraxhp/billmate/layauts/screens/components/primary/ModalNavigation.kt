package com.feraxhp.billmate.layauts.screens.components.primary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModalNavigation(
    items: List<ImageVector> = listOf(Icons.Outlined.Home),
    itemsBold: List<ImageVector> = listOf(Icons.Filled.Home),
    text: List<String> = listOf("Home"),
    drawerState: DrawerState,
    scope: CoroutineScope,
    selectedItem: Int, onItemClick: (Int) -> Unit,
    content: @Composable () -> Unit,
) {
    val selection = try {
        items[selectedItem]
    } catch (_: Exception) {
        null
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    val selectioned = selection == item
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                if (!selectioned) item else itemsBold[items.indexOf(item)],
                                contentDescription = null
                            )
                        },
                        label = { Text(text = text[items.indexOf(item)]) },
                        selected = selectioned,
                        onClick = {
                            scope.launch { drawerState.close() }
                            onItemClick(items.indexOf(item))
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                            .height(50.dp)
                    )
                }
            }
        },
        content = {
            content()
        })
}
