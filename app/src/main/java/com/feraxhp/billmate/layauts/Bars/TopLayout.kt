package com.feraxhp.billmate.layauts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopLayout(modifier: Modifier = Modifier) {
    val modifierIcon: Modifier = Modifier.widthIn(10.dp, 20.dp)
    Row(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(end = 10.dp)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Menu,
                contentDescription = "icon",
                modifier = modifierIcon
//                .weight(1f)
//                .padding(10.dp)
//                .clickable { }
            )
        }
        Text(
            text = "Billmate",
            modifier = Modifier
                .weight(8f)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(end = 10.dp)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {

            Icon(
                Icons.Default.Search,
                contentDescription = "icon",
                modifier = modifierIcon
            )
        }
    }

}

@Preview(widthDp = 400)
@Composable
fun Preview() {
    TopLayout()
}