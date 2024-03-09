package com.example.coinmarket.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignInUpViewModel : ViewModel() {

    val signInEmail = mutableStateOf("")
    val signInPassword = mutableStateOf("")

    val signUpEmail = mutableStateOf("")
    val signUpPassword = mutableStateOf("")
    val signUpConfirmPassword = mutableStateOf("")

}