package com.example.coinmarket.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.coinmarket.R
import com.example.coinmarket.ui.feature.CalculatorScreen
import com.example.coinmarket.ui.feature.DetailScreen
import com.example.coinmarket.ui.feature.HelpScreen
import com.example.coinmarket.ui.feature.HomeScreen
import com.example.coinmarket.ui.feature.IntroScreen
import com.example.coinmarket.ui.feature.SearchScreen
import com.example.coinmarket.ui.feature.SignInScreen
import com.example.coinmarket.ui.feature.SignUpScreen
import com.example.coinmarket.ui.theme.CoinMarketTheme
import com.example.coinmarket.ui.theme.Gradient1
import com.example.coinmarket.ui.theme.Gradient2
import com.example.coinmarket.ui.theme.Gradient3
import com.example.coinmarket.util.MyScreens
import com.example.coinmarket.viewModel.MainViewModel
import com.example.coinmarket.viewModel.SignInUpViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Gradient1,
                                    Gradient2,
                                    Gradient3
                                )
                            )
                        ),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    AsyncImage(
                        model = R.drawable.the_crypto_app,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 12.dp, bottom = 12.dp)
                            .size(100.dp)
                        )

                    Text(
                        text = "Coin Market Cap",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White ,
                        modifier = Modifier.padding(start = 12.dp , bottom = 24.dp)
                        )

                    Text(
                        text = "\"With Crypto app, monitor digital currency prices and make informed decisions.\"",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light
                        ),
                        color = Color.White ,
                        modifier = Modifier.padding(start = 12.dp , bottom = 24.dp)
                    )

                }

                Divider()

                NavigationDrawerItem(
                    label = { Text(text = "Bookmark") },
                    selected = false,
                    icon = { Icon(painter = painterResource(R.drawable.outline_bookmark_border_24), contentDescription = null) },
                    onClick = {  }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Setting") },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Outlined.Settings, contentDescription = null) },
                    onClick = {  }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Info") },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Outlined.Info, contentDescription = null) },
                    onClick = {  }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Help") },
                    selected = false,
                    icon = { Icon(painter = painterResource(R.drawable.outline_help_outline_24), contentDescription = null) },
                    onClick = {
                        navController.navigate(MyScreens.HelpScreen.route)
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
                // ...other drawer items
            }
        }
    ) {
        // Screen content

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

            composable(MyScreens.HelpScreen.route){
                HelpScreen()
            }


        }

    }
}

