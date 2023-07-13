package com.feraxhp.billmate.layauts.screens.components.primary

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.activitys.ui.theme.Elevations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    text: String,
    navigationAction: () -> Unit,
    navigationIcon: ImageVector? = Icons.Filled.Menu,
    actionIconAction: () -> Unit = {},
    scrollState: Int = 0,
    actionIcon: ImageVector? = Icons.Filled.Settings,
) {
    val color = if (scrollState != 0) MaterialTheme.colorScheme.surfaceColorAtElevation(Elevations.level2) else MaterialTheme.colorScheme.background
    val onColor = if (scrollState != 0) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground
    TopAppBar(
        title = {
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = { navigationAction() }) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = "Localized description",
                        tint = onColor
                    )
                }
            }
        },
        actions = {
            if (actionIcon != null) {
                IconButton(onClick = { actionIconAction() }) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = "Localized description",
                        tint = onColor
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = color,
            titleContentColor = onColor,
            navigationIconContentColor = onColor
        )
    )
}
@Composable
fun MyTopAppBar(
    text: String,
    NavigationActionComposable: @Composable () -> Unit,
    navigationIcon: ImageVector? = Icons.Filled.Menu,
    searchActionComposable: @Composable () -> Unit = {},
    searchIcon: ImageVector? = Icons.Filled.Search,
    scrollState: Int = 0,
) {
    var navigationPressed by remember {mutableStateOf(false)}
    if (navigationPressed) {
        NavigationActionComposable()
        navigationPressed = false
    }
    var searchPressed by remember {mutableStateOf(false)}
    if (searchPressed) {
        searchActionComposable()
        searchPressed = false
    }
    MyTopAppBar(
        text = text,
        navigationAction = {navigationPressed = true},
        navigationIcon = navigationIcon,
        actionIconAction = { searchPressed = true},
        scrollState = scrollState,
        actionIcon = searchIcon
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 400)
@Composable
fun Preview() {
    MyTopAppBar(navigationAction = {}, actionIconAction = {}, text = "Billmate")
}