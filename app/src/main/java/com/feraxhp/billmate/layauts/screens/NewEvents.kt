package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.activitys.MainActivity.Companion.viewController
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewEvents() {
    BillmateTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "New Events",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                viewController.startMainActivity()
                            }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "")
                        }
                    }
                )
            },
            content = {

            },
        )
    }
}


@Preview
@Composable
fun NewEventsPreview() {
    NewEvents()
}