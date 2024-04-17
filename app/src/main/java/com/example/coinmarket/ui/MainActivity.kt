package com.example.coinmarket.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Divider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coinmarket.ui.feature.CalculatorScreen
import com.example.coinmarket.ui.feature.DetailScreen
import com.example.coinmarket.ui.feature.HomeScreen
import com.example.coinmarket.ui.feature.IntroScreen
import com.example.coinmarket.ui.feature.SearchScreen
import com.example.coinmarket.ui.feature.SignInScreen
import com.example.coinmarket.ui.feature.SignUpScreen
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
    private val signInUpViewModel: SignInUpViewModel by viewModels()
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            CoinMarketTheme {
                UiScreen(viewModel, signInUpViewModel, {
                    signInUser(it)
                }, {
                    signUpUser(it)
                }
                )

            }
        }

    }

    //firebase
    private fun signInUser(navHostController: NavHostController) {
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(
            signInUpViewModel.signInEmail.value,
            signInUpViewModel.signInPassword.value
        )
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

                    navHostController.navigate(MyScreens.HomeScreen.route) {

                        popUpTo(MyScreens.SignInScreen.route) {
                            inclusive = true
                        }

                    }

                } else {

                    Toast.makeText(this, "Check Email or Password ", Toast.LENGTH_SHORT)
                        .show()

                }

            }
    }

    private fun signUpUser(navHostController: NavHostController) {
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(
            signInUpViewModel.signUpEmail.value, signInUpViewModel.signUpPassword.value
        ).addOnCompleteListener(this) { task ->

            if (task.isSuccessful) {

                Toast.makeText(this, "Successful registration", Toast.LENGTH_SHORT).show()
                val pref = this.getSharedPreferences(
                    "Successful SignIn", Context.MODE_PRIVATE
                )
                val editor = pref.edit()
                editor.putString("signIn", "successful")
                editor.apply()

                navHostController.navigate(MyScreens.HomeScreen.route) {

                    popUpTo(MyScreens.SignInScreen.route) {
                        inclusive = true
                    }

                }
            } else {
                Toast.makeText(this, "Registration not successful ", Toast.LENGTH_SHORT).show()
            }


        }
    }
}

@Composable
fun UiScreen(
    viewModel: MainViewModel,
    signInUpViewModel: SignInUpViewModel,
    onSignInClicked: (NavHostController) -> Unit,
    onSignUpClicked: (NavHostController) -> Unit
) {


    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {

                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                // ...other drawer items
            }
        }
    ) {
        // Screen content

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = MyScreens.IntroScreen.route) {

            composable(MyScreens.HomeScreen.route) {
                HomeScreen(viewModel, navController)
            }

            composable(
                route = MyScreens.DetailScreen.route + "/{cryptoId}",
                arguments = listOf(navArgument("cryptoId") { type = NavType.IntType })
            ) {
                DetailScreen(viewModel, it.arguments!!.getInt("cryptoId", 0), navController)
            }

            composable(MyScreens.SearchScreen.route) {
                SearchScreen(viewModel, navController)
            }

            composable(MyScreens.IntroScreen.route) {
                IntroScreen(navController)
            }

            composable(MyScreens.SignInScreen.route) {
                SignInScreen(signInUpViewModel, navController) {
                    onSignInClicked.invoke(navController)
                }
            }

            composable(MyScreens.SignUpScreen.route) {
                SignUpScreen(signInUpViewModel, navController) {
                    onSignUpClicked.invoke(navController)
                }
            }

            composable(
                route = MyScreens.CalculatorScreen.route + "/{cryptoPrice}",
                arguments = listOf(navArgument("cryptoPrice") { type = NavType.FloatType })
            ) {
                CalculatorScreen(
                    viewModel,
                    navController,
                    it.arguments!!.getFloat("cryptoPrice", -1.1f)
                )
            }


        }

    }
}

