package com.feraxhp.billmate.layauts.screens.components.primary

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.controllers.dependencies.Activities
import com.feraxhp.billmate.extrendedFuntions.backups.backupDatabase
import com.feraxhp.billmate.layauts.tabs.components.components.MyAlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
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
                Column {
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
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                Icons.Default.Build,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = "Create Backup") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            Toast.makeText(viewController.fatherContext, backupDatabase(appController.context), Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                            .height(50.dp)
                    )
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                Icons.Default.AccountBox,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = "Restore Backup") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            viewController.startActivity(Activities.getFiles)
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
