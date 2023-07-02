package com.feraxhp.billmate.layauts.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.ui.theme.Elevations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    text: String,
    navigationAction: () -> Unit,
    searchAction: () -> Unit,
    scrollState: Int = 0
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
            IconButton(onClick = { navigationAction() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                    tint = onColor
                )
            }
        },
        actions = {
            IconButton(onClick = { searchAction() }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Localized description",
                    tint = onColor
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = color,
            titleContentColor = onColor,
            navigationIconContentColor = onColor
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 400)
@Composable
fun Preview() {
    MyTopBar(navigationAction = {}, searchAction = {}, text = "Billmate")
}