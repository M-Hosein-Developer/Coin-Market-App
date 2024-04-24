package com.example.coinmarket.ui.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HelpScreen(navController: NavHostController) {

    val helpTitle = listOf(
        "What is \"Market Capitalization\" and how is it calculated?",
        "What is the difference between \"Circulating Supply\", \"Total Supply\", and \"Max Supply\"?",
        "Why are markets with no fees excluded from the price average and total trading volume?",
        "At what time is the 24 hour % change based?",
        "Why does a question mark sometimes show up for the circulating supply and market cap of a cryptocurrency?",
        "In what time zone is the site based?",

        )
    val helpDesc = listOf(
        "Market Capitalization is one way to rank the relative size of a cryptocurrency. It's calculated by multiplying the Price by the Circulating Supply.\n" +
                "\n" +
                "Market Cap = Price X Circulating Supply.",

        "Circulating Supply is the best approximation of the number of coins that are circulating in the market and in the general public's hands.\n" +
                "Total Supply is the total amount of coins in existence right now (minus any coins that have been verifiably burned).\n" +
                "Max Supply is the best approximation of the maximum amount of coins that will ever exist in the lifetime of the cryptocurrency.",

        "When no fees are being charged at the exchange, it is possible for a trader (or bot) to trade back and forth with themselves and generate a lot of \"fake\" volume without penalty. It's impossible to determine how much of the volume is fake so we exclude it entirely from the calculations.",

        "It's based on the current time. It's a rolling 24 hour period.",

        "In order to ensure accurate market cap rankings, we work closely with teams and developers to verify supply details on their respective blockchains. If a question mark shows up, it means that we have not sufficiently verified the circulating supply and resulting market cap yet.",

        "Data is collected, recorded, and reported in UTC time unless otherwise specified."
    )

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        HelpToolbar {
            navController.popBackStack()
        }

        for (i in helpTitle.indices) {

            HelpText(helpTitle[i] , helpDesc[i])
            HorizontalDivider(
                Modifier.padding(vertical = 18.dp)
            )

        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpToolbar(onBackPress :() -> Unit ){

    TopAppBar(
        title = { Text(
            text = "Help",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 8.dp)
        ) },
        navigationIcon = {
            IconButton(onClick = { onBackPress.invoke() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }
        }
        )

}

@Composable
fun HelpText(title: String, desc: String) {


    Column(
        Modifier.padding(horizontal = 18.dp)
    ) {

        Text(
            text = title,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 12.dp)
        )


        Text(
            text = desc,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier.padding(top = 12.dp)
        )

    }

}