package com.feraxhp.billmate.layauts.components.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feraxhp.billmate.layauts.components.tabs.components.components.MyCards

@Composable
fun HomeTab() {
    Column() {
        Column() {
            MyCards (
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp)
                    .height(150.dp),
            ){
                Text(text = "Home")
            }
            Row() {
                MyCards() {
                    
                }
                MyCards() {
                    
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun HomeTabPreview() {
    HomeTab()
}