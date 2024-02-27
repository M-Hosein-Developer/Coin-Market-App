package com.example.coinmarket.ui

import android.os.Bundle
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

        setContent {
            CoinMarketTheme {

                UiScreen(viewModel)

            }
        }
    }
}

@Composable
fun UiScreen(viewModel: MainViewModel) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MyScreens.HomeScreen.route){

        composable(MyScreens.HomeScreen.route){
            HomeScreen(viewModel , navController)
        }

        composable(MyScreens.DetailScreen.route){
            DetailScreen(viewModel)
        }

        composable(MyScreens.SearchScreen.route){
            SearchScreen(viewModel)
        }

    }

}

