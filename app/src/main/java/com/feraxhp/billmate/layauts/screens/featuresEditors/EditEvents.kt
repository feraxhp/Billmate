package com.feraxhp.billmate.layauts.screens.featuresEditors

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.layauts.screens.components.primary.MyFloatingActionButton
import com.feraxhp.billmate.layauts.screens.components.primary.MyTopAppBar
import com.feraxhp.billmate.layauts.screens.featuresEditors.components.events.Editable
import com.feraxhp.billmate.layauts.screens.featuresEditors.components.events.NoEditable
import com.feraxhp.billmate.logic_database.database.entities.Events

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditEvents(
    Event: Events = Events(
        name = "ExampleName",
        amount = 0.0,
        description = "ExampleDescription",
        date = 0L,
        time = "ExampleTime",
        type = false,
        fund_id = 0L,
        category_id = 0L
    ),
) {

    var isError by remember { mutableStateOf(false) }
    var editedEvent by remember {
        mutableStateOf(
            Events(
                name = "ExampleName",
                amount = 0.0,
                description = "ExampleDescription",
                date = 0L,
                time = "ExampleTime",
                type = false,
                fund_id = 0L,
                category_id = 0L
            )
        )
    }
    var isEditable by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MyTopAppBar(
                text = Event.name,
                NavigationActionComposable = {
                    if (isEditable) isEditable = false else viewController.finishActivity()
                },
                navigationIcon = Icons.Filled.ArrowBack,
                searchActionComposable = { isEditable = !isEditable },
                searchIcon = if (isEditable) Icons.Filled.Close else Icons.Filled.Edit
            )
        },
        content = {
            when (isEditable) {
                true -> Editable(
                    Event = Event,
                    paddingValues = it,
                    isError = { isError = it }) { editedEvent = it }

                false -> NoEditable(Event = Event, paddingValues = it)
            }
        },
        floatingActionButton = {
            when (isEditable) {
                true -> MyFloatingActionButton(
                    text = "Save",
                    withIcon = false,
                    onClick = {
                        if (!isError && editedEvent != Event) {
                            val response = appController.editEvent(
                                editedEvent
                            )
                            if (response) {
                                viewController.finishActivityWithActualize()
                            }
                        } else if (editedEvent == Event) {
                            isEditable = false
                        }
                    }
                )
                false -> {}
            }
        }
    )
}


@Preview
@Composable
fun EditEventsPreview() {
    EditEvents()
}