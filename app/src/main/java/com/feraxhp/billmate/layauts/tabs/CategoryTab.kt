package com.feraxhp.billmate.layauts.tabs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.layauts.tabs.components.CategoriesMessage

@Composable
fun CategoryTab(padding: PaddingValues = PaddingValues(0.dp)) {
    var list by remember { mutableStateOf(appController.getAllCategories()) }
    LazyColumn(
        modifier = Modifier
            .padding(padding),
    ) {
        items(list.toMutableList().size) {
            CategoriesMessage(list[it].name, list[it].amount) {
                appController.removeCategory(list[it])
                list = list
                    .toMutableList()
                    .apply { remove(list[it]) }
            }
        }
    }
}

@Preview
@Composable
fun CategoryTabPreview() {
    CategoryTab()
}