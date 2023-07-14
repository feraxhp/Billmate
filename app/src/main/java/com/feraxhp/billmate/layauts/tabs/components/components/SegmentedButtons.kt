package com.feraxhp.billmate.layauts.tabs.components.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SegmentedButtons(
    modifier: Modifier = Modifier,
    values: List<String> = listOf("Selected", "Enable", "Enable", "Disabled"),
    selectedValue: Int = 0,
    setSelectedValue: (Int) -> Unit = {},
    disabled: List<Boolean> = listOf(false, false, false, true),
) {
    val height: Dp = 50.dp
    val borderSize: Dp = 1.dp
    val borderMediumSize: Dp = 0.dp

    val enableColor = MaterialTheme.colorScheme.onSurface
    val disableColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)

    Row(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        for (index in values.indices) {
            val color =
                if (index == selectedValue) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
            val onColor =
                if (index == selectedValue) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurface
            val shape =
                if (index == 0) RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)
                else if (index == values.size - 1) RoundedCornerShape(
                    topEndPercent = 50,
                    bottomEndPercent = 50
                )
                else RoundedCornerShape(0)
            Box(
                modifier = Modifier
                    .height(height)
                    .weight(1f)
                    .clip(shape)
                    .background(
                        if (disabled[index]) disableColor
                        else enableColor
                    )
                    .padding(
                        if (index == 0) PaddingValues(
                            top = borderSize,
                            bottom = borderSize,
                            start = borderSize,
                            end = borderMediumSize
                        )
                        else if (index == values.size - 1) PaddingValues(
                            top = borderSize,
                            bottom = borderSize,
                            start = borderMediumSize,
                            end = borderSize
                        )
                        else PaddingValues(
                            top = borderSize,
                            bottom = borderSize,
                            start = borderMediumSize,
                            end = borderMediumSize
                        )
                    )
                    .background(
                        color,
                        shape
                    )
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource(),
                    ){
                        if (!disabled[index]) setSelectedValue(index)
                    }
                ,
                contentAlignment = Alignment.Center
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
                            tint =
                            if (disabled[index]) disableColor
                            else onColor,
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(18.dp)
                        )
                    }
                    Text(
                        text = values[index],
                        color =
                        if (disabled[index]) disableColor
                        else onColor
                    )
                }
            }
            if (index != values.size - 1) Box(
                modifier = Modifier
                    .background(
                        if (disabled[index] && disabled[index+1]) disableColor
                        else enableColor
                    )
                    .height(height)
                    .padding(horizontal = borderSize/2)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SegmentedButtonsPreview() {
    SegmentedButtons()
}