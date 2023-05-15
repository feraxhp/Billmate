package com.feraxhp.billmate.layauts.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feraxhp.billmate.activitys.MainActivity.Companion.appController
import com.feraxhp.billmate.layauts.tabs.components.components.MyCardsFunds

@Composable
fun FundsTab(innerPadding: PaddingValues = PaddingValues(0.dp)) {
    var list by remember { mutableStateOf(appController.getAllFunds()) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        /*.background(MaterialTheme.colorScheme.primaryContainer)*/
        contentPadding = innerPadding,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(list.toMutableList()) { fund ->
            MyCardsFunds(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .height(200.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopStart
                ) {

                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .clickable {
                                appController.removeFund(fund)
                                list = list
                                    .toMutableList()
                                    .apply { remove(fund) }
                            }
                            .padding(top = 10.dp, end = 5.dp)
                            .size(18.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Text(text = fund.accountName, fontSize = 18.sp, modifier = Modifier)
                        Text(text = fund.titularName, fontSize = 14.sp, modifier = Modifier)
                    }
                    Text(
                        text = fund.description,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .align(Alignment.CenterStart)
                    )
                    Text(
                        text = "Balance: ${fund.amount}",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun FundsTabPreview() {
    FundsTab()
}