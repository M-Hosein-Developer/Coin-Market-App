package com.example.coinmarket.ui.feature

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.coinmarket.viewModel.MainViewModel

@Composable
fun DetailScreen(viewModel: MainViewModel, int: Int) {


    Text(text = int.toString())
    
}