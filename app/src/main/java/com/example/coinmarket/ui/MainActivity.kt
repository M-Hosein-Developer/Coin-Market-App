package com.example.coinmarket.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coinmarket.ui.feature.DetailScreen
import com.example.coinmarket.ui.feature.HomeScreen
import com.example.coinmarket.ui.feature.IntroScreen
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

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

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
    NavHost(navController = navController, startDestination = MyScreens.IntroScreen.route){

        composable(MyScreens.HomeScreen.route){
            HomeScreen(viewModel , navController)
        }

        composable(
            route = MyScreens.DetailScreen.route + "/{cryptoId}",
            arguments = listOf(navArgument("cryptoId"){type = NavType.IntType})
        ){
            DetailScreen(viewModel , it.arguments!!.getInt("cryptoId" , 0))
        }

        composable(MyScreens.SearchScreen.route){
            SearchScreen(viewModel , navController)
        }

        composable(MyScreens.IntroScreen.route){
            IntroScreen(navController)
        }

    }

}

