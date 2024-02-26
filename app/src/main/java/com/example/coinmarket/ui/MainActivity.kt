package com.example.coinmarket.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coinmarket.ui.feature.DetailScreen
import com.example.coinmarket.ui.feature.HomeScreen
import com.example.coinmarket.ui.feature.SearchScreen
import com.example.coinmarket.ui.theme.CoinMarketTheme
import com.example.coinmarket.util.MyScreens
import com.example.coinmarket.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCryptoList{
            Log.v("testData1",it.toString())
        }

        setContent {
            CoinMarketTheme {

                UiScreen()

            }
        }
    }
}

@Composable
fun UiScreen() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MyScreens.HomeScreen.route){

        composable(MyScreens.HomeScreen.route){
            HomeScreen()
        }

        composable(MyScreens.DetailScreen.route){
            DetailScreen()
        }

        composable(MyScreens.SearchScreen.route){
            SearchScreen()
        }

    }

}

