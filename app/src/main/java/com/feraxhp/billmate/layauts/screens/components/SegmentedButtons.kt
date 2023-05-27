package com.feraxhp.billmate.layauts.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SegmentedButtons(
    buttonNames: List<String> = listOf("Selected", "Enable", "Enable"),
    selectedValue: Int = 1,
    onItemClick: (Int) -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                shape = RoundedCornerShape(50.dp),
                color = MaterialTheme.colorScheme.surface
            )
            .border(
                width = .5.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        for (index in buttonNames.indices) {
            val onColor: Color = when (index) {
                selectedValue -> MaterialTheme.colorScheme.onSecondaryContainer
                else -> MaterialTheme.colorScheme.onSurface
            }
            val borderModifier: Modifier = if (
                index != 0 &&
                index != buttonNames.size - 1
            ) {
                Modifier.border(width = .5.dp, color = MaterialTheme.colorScheme.onSurface)
            } else {
                Modifier
            }
            var backgroundModifier: Modifier  = Modifier
            if (index == selectedValue) {
                backgroundModifier = when (index) {
                    0 -> {
                        Modifier.background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(
                                bottomStartPercent = 50,
                                topStartPercent = 50
                            )
                        )
                    }

                    buttonNames.size - 1 -> {
                        Modifier.background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(bottomEndPercent = 50, topEndPercent = 50)
                        )
                    }

                    else -> {
                        Modifier.background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                        )
                    }
                }
            }


            Box(
                modifier = borderModifier
                    .then(backgroundModifier)
                    .weight(1f),
            ) {
                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onItemClick(index) }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        if (index == selectedValue) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "",
                                tint = onColor,
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .size(18.dp)
                            )
                        }
                        Text(
                            text = buttonNames[index],
                            color = onColor
                        )
                    }
                }
            }
        }
    }
}

@Preview( uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SegmentedButtonsPreview() {
    SegmentedButtons()
}