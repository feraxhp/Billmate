package com.feraxhp.billmate.layauts.screens.featuresCreation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.layauts.screens.components.primary.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.primary.MyTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewCategory() {


    val labels = listOf("Category Name", "Description")
    val (categoryName, setCategoryName) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }
    val values = listOf(categoryName, description)
    val setters = listOf(setCategoryName, setDescription)
    val errorName = remember { mutableStateOf(false) }
    val errorAmount = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(
                text = "New Category",
                NavigationActionComposable = { viewController.finishActivity() },
                navigationIcon = Icons.Filled.ArrowBack,
                searchIcon = null
            )
        },
        content = { paddingValues ->
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(labels.size) { position ->
                    OutlinedTextField(
                        value = values[position],
                        onValueChange = {
                            setters[position](it)
                            if (position == 0) errorName.value = false
                            if (position == 1) errorAmount.value = false
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            errorBorderColor = MaterialTheme.colorScheme.error,
                        ),
                        maxLines = 1,
                        label = { Text(labels[position]) },
                        shape = MaterialTheme.shapes.small,
                        isError = (position == 0 && errorName.value) || (position == 1 && errorAmount.value),
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 10.dp)
                    )
                }
            }
        },
        floatingActionButton = {
            MyFloatingActionButton(
                text = "Save",
                onClick = {
                    val response = appController.addCategory(
                        categoryName, "", description
                    )
                    when (response) {
                        1 -> {
                            errorName.value = true
                        }

                        2 -> {
                            errorAmount.value = true
                        }

                        else -> {
                            errorName.value = false
                            errorAmount.value = false
                            viewController.finishActivityWithActualize()
                        }
                    }
                },
            )
        }
    )
}


@Preview
@Composable
fun NewCategoryPreview() {
    NewCategory()
}