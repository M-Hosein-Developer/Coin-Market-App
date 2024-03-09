package com.example.coinmarket.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coinmarket.ui.feature.DetailScreen
import com.example.coinmarket.ui.feature.HomeScreen
import com.example.coinmarket.ui.feature.IntroScreen
import com.example.coinmarket.ui.feature.SearchScreen
import com.example.coinmarket.ui.feature.SignInScreen
import com.example.coinmarket.ui.theme.CoinMarketTheme
import com.example.coinmarket.util.MyScreens
import com.example.coinmarket.viewModel.MainViewModel
import com.example.coinmarket.viewModel.SignInUpViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val signInUpViewModel : SignInUpViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        setContent {
            CoinMarketTheme {

                UiScreen(viewModel, signInUpViewModel, {
                    signInUser(it)
                }
                )

            }
        }

    }

    //firebase32
    private fun signInUser(navHostController: NavHostController) {
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(signInUpViewModel.email.value, signInUpViewModel.password.value)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this, "Successful SignIn", Toast.LENGTH_SHORT).show()

                    val pref = this.getSharedPreferences(
                        "Successful SignIn",
                        Context.MODE_PRIVATE
                    )
                    val editor = pref.edit()
                    editor.putString("signIn", "successful")
                    editor.apply()

                    navHostController.navigate(MyScreens.HomeScreen.route){

                        popUpTo(MyScreens.SignInScreen.route){
                            inclusive = true
                        }

                    }

                } else {

                    Toast.makeText(this, "Check Email or Password ", Toast.LENGTH_SHORT)
                        .show()

                }
            }
    }

}

@Composable
fun UiScreen(
    viewModel: MainViewModel,
    signInUpViewModel: SignInUpViewModel,
    onSignInClicked: (NavHostController) -> Unit
) {

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

        composable(MyScreens.SignInScreen.route){
            SignInScreen(signInUpViewModel) {
                onSignInClicked.invoke(navController)
            }
        }

    }

}

