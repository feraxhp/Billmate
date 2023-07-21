package com.feraxhp.billmate.layauts.screens.featuresCreation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.controllers.dependencies.CategoryStructure
import com.feraxhp.billmate.logic_database.database.entities.Categories

private val category1 = CategoryStructure(
    self = Categories(
        name = "Father",
        icon = 0,
        amount = 0.0,
        description = "This is an example of a category"
    ),
    sons = mutableListOf(
        CategoryStructure(
            self = Categories(
                name = "Son 1",
                icon = 0,
                amount = 0.0,
                description = "This is an example of a category"
            )
        ),
        CategoryStructure(
            self = Categories(
                name = "son 2",
                icon = 0,
                amount = 0.0,
                description = "This is an example of a category"
            )
        ),
        CategoryStructure(
            self = Categories(
                name = "son 3",
                icon = 0,
                amount = 0.0,
                description = "This is an example of a category"
            ),
            sons = mutableListOf(
                CategoryStructure(
                    self = Categories(
                        name = "subson 4",
                        icon = 0,
                        amount = 0.0,
                        description = "This is an example of a category"
                    )
                ),
                CategoryStructure(
                    self = Categories(
                        name = "subson 5",
                        icon = 0,
                        amount = 0.0,
                        description = "This is an example of a category"
                    ),
                    CategoryStructure(
                        self = Categories(
                            name = "subson 6",
                            icon = 0,
                            amount = 0.0,
                            description = "This is an example of a category"
                        ),
                    )
                )
            )
        )
    )
)
private val category2 = CategoryStructure(
    self = Categories(
        name = "Father 2",
        icon = 0,
        amount = 0.0,
        description = "This is an example of a category"
    ),
    sons = mutableListOf(
        CategoryStructure(
            self = Categories(
                name = "Son 1",
                icon = 0,
                amount = 0.0,
                description = "This is an example of a category"
            )
        ),
        CategoryStructure(
            self = Categories(
                name = "son 2",
                icon = 0,
                amount = 0.0,
                description = "This is an example of a category"
            )
        ),
        CategoryStructure(
            self = Categories(
                name = "son 3",
                icon = 0,
                amount = 0.0,
                description = "This is an example of a category"
            ),
            sons = mutableListOf(
                CategoryStructure(
                    self = Categories(
                        name = "subson 4",
                        icon = 0,
                        amount = 0.0,
                        description = "This is an example of a category"
                    )
                ),
                CategoryStructure(
                    self = Categories(
                        name = "subson 5",
                        icon = 0,
                        amount = 0.0,
                        description = "This is an example of a category"
                    ),
                    CategoryStructure(
                        self = Categories(
                            name = "subson 6",
                            icon = 0,
                            amount = 0.0,
                            description = "This is an example of a category"
                        ),
                    )
                )
            )
        )
    )
)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelector(
    CategoriesList: List<CategoryStructure> = listOf(category1, category2),
    setSelectedCategory: (Categories) -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(),
){
    val (expanded: Boolean, setExpanded: (Boolean) -> Unit) = remember { mutableStateOf(false) }
    val selectedCategory = remember { mutableStateOf(CategoriesList[0].self) }
    val requester = remember {
        FocusRequester()
    }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { setExpanded(!expanded) },
        modifier = Modifier
            .fillMaxWidth()
//            .then(modifier)
    ) {
        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(requester)
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) },
            modifier = Modifier
        ) {
                DropdownMenuItem(
                    colors = MenuDefaults.itemColors(
                        textColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    text = {
                        if (CategoriesList[1].hasSons()) {
                            CategorySelector(CategoriesList[1].getAllSons(), paddingValues = PaddingValues(top = 10.dp))
                        }
                    },
                    onClick = {
                        /*setSelectedOptionText(selectionOption)
                        setExpanded(!expanded)*/
                    },
                    contentPadding = PaddingValues(top = 10.dp + paddingValues.calculateTopPadding()),
                    modifier = Modifier
                        /*.background(
                            color = optionColors[colorsSelector[options.indexOf(selectionOption)]],
                        )*/
                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prueba(
    expanded: Boolean,
    setExpanded: (Boolean) -> Unit,
    value: String,
){
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = MaterialTheme.shapes.small
            )
            .clickable { setExpanded(!expanded) }
        ,
        readOnly = true,
        value = value,
        onValueChange = {},
        label = {
            Text(
                "label"
            )
        },
        trailingIcon = {
            ExposedDropdownMenuDefaults.TrailingIcon(
                expanded = expanded
            )
        },
        colors = ExposedDropdownMenuDefaults.textFieldColors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0f
            ),
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0f
            ),
        ),
        shape = MaterialTheme.shapes.small,
    )
}


@Preview
@Composable
private fun CategorieSelectorPreview() {
    CategorySelector()
}