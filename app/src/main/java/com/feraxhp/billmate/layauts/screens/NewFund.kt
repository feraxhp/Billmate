package com.feraxhp.billmate.layauts.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.activitys.ui.theme.BillmateTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewFund() {
    BillmateTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            var value by remember { mutableStateOf("") }
//            TextField(value = value, onValueChange = )
        }
    }
}

@Preview
@Composable
fun NewFundPreview() {
    NewFund()
}