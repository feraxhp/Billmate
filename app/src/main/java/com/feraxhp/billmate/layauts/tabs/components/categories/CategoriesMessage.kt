package com.feraxhp.billmate.layauts.tabs.components.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feraxhp.billmate.activitys.MainActivity
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.extrendedFuntions.toMoneyFormat

@Composable
fun CategoriesMessage(
    icon: Int? = null,
//    father: Long = 0L,
    name: String = "Example",
    amount: Double = 0.0,
    persentage: Float = 0f,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                        1.0f to MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(1.dp)
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to MaterialTheme.colorScheme.surfaceVariant,
                        1.0f to MaterialTheme.colorScheme.surface
                    )
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(10.dp)
    ) {

        val tint = MaterialTheme.colorScheme.onSurfaceVariant
        val modifier = Modifier
            .padding(10.dp)
        if (icon == null) Icon(
            Icons.Default.Build,
            contentDescription = "",
            tint = tint,
            modifier = modifier
        )
        else Icon(
            painterResource(appController.icons.getResourceId(icon, 0)),
            contentDescription = "",
            tint = tint,
            modifier = modifier
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
                text = "Amount: ${amount.toMoneyFormat()} ${if ((persentage*100).toInt() == 0) "" else " ~ " + (persentage*100).toInt().toString()+"%"}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .border(
                        width = 3.dp,
                        brush = Brush.horizontalGradient(
                            colorStops = arrayOf(
                                0.0f to MaterialTheme.colorScheme.primary.copy(alpha = .9f),
                                persentage to MaterialTheme.colorScheme.primary.copy(
                                    alpha = .5f
                                ),
                                persentage to MaterialTheme.colorScheme.primary.copy(
                                    alpha = .0f
                                ),
                                1.0f to MaterialTheme.colorScheme.primary.copy(alpha = .0f)
                            )
                        ),
                        shape = MaterialTheme.shapes.small
                    )
                    .height(if ((persentage*100).toInt() == 0) 0.dp else 6.dp)
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