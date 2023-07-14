package com.feraxhp.billmate.layauts.tabs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.layauts.tabs.components.categories.CategoriesMessage
import com.feraxhp.billmate.layauts.tabs.components.components.MyAlertDialog

@Composable
fun CategoryTab(
    padding: PaddingValues = PaddingValues(0.dp),
    hasNoItems: (Boolean) -> Unit = {},
    setScrollState: (Int) -> Unit = {},
    ) {
    var list by remember { mutableStateOf(appController.getAllCategories()) }
    val lazyListState = rememberLazyListState()
    val scrollValue by remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    setScrollState(scrollValue)

    if (list.isEmpty()) {
        hasNoItems(true)
    }else {
        hasNoItems(false)
    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(padding),
        state = lazyListState,
        ) {
        items(list.toMutableList().size) {
            // Confirmation alert dialog
            var showDialog by remember { mutableStateOf(false) }
            CategoriesMessage(list[it].name, list[it].amount) {
                showDialog = true
            }
            Spacer(modifier = Modifier.padding(8.dp))
            MyAlertDialog(
                openState = showDialog,
                setOpenState = { showDialog = it },
                title = "Delete this category?",
                message = "If you delete this category, you will not be able to recover it, and it will delete all transactions related to this fund.",
                onConfirm = {
                    appController.removeCategory(list[it])
                    list = list
                        .toMutableList()
                        .apply {
                            remove(list[it])
                        }
                }
            )
        }
    }
}

@Preview
@Composable
fun CategoryTabPreview() {
    CategoryTab()
}