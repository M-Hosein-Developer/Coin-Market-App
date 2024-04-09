package com.example.coinmarket.ui.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.coinmarket.util.EmptyDollar
import com.example.coinmarket.viewModel.MainViewModel

@Composable
fun CalculatorScreen(
    viewModel: MainViewModel,
    navController1: NavHostController,
    coinPrice : Float
) {

    var dollarPrice by remember { mutableStateOf(EmptyDollar) }

    LaunchedEffect(coinPrice) {

        viewModel.getDollarPrice.collect{
            if (it != null)
            dollarPrice = it
        }

    }



}