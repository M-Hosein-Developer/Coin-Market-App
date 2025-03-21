package com.example.coinmarket.util

sealed class MyScreens(val route : String){

    object HomeScreen : MyScreens("homeScreen")
    object DetailScreen : MyScreens("detailScreen")
    object SearchScreen : MyScreens("searchScreen")
    object SignUpScreen : MyScreens("signUpScreen")
    object SignInScreen : MyScreens("signInScreen")
    object IntroScreen : MyScreens("introScreen")
    object CalculatorScreen : MyScreens("calculatorScreen")
    object HelpScreen : MyScreens("helpScreen")
    object InfoScreen : MyScreens("infoScreen")
    object SettingScreen : MyScreens("settingScreen")
    object BookmarkScreen : MyScreens("bookmarkScreen")
    object MoreDetailScreen : MyScreens("moreDetailScreen")

}
