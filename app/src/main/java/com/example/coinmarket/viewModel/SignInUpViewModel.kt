package com.example.coinmarket.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignInUpViewModel : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")


}