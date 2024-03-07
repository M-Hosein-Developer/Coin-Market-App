package com.example.coinmarket.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")


}