package com.feraxhp.billmate.layauts.tabs.components.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat

@Composable
fun CategoriesMessage(
    icon: Int = 0,
    name: String = "Example",
    amount: Double = 0.0,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                width = 1.dp,
                shape = MaterialTheme.shapes.small
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            .padding(10.dp)
    ) {
        Icon(
            painterResource(
                id = appController.icons.getResourceId(icon, 0)
            ),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .padding(10.dp)
        )
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 10.dp),
        ) {
            Text(
                text = name,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Amount: ${amount.toMoneyFormat()}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        IconButton(onClick = { onClick() }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Preview
@Composable
fun CategoriesMessagePreview() {
    CategoriesMessage()
}