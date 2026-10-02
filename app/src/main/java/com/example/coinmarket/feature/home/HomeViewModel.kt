package com.example.coinmarket.feature.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinmarket.util.EmptyCoinList
import com.example.coinmarket.util.EmptyDollar
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.Model.Price
import ir.androidcoder.usecases.main.MainUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val usecase: MainUsecase) : ViewModel() {

    val getCryptoList = mutableStateOf(EmptyCoinList)
    val getDollarPrice = MutableStateFlow<Price>(EmptyDollar)

    init {
        getCryptoList()
        getDollarPrice()
    }

    fun getCryptoList() {
        viewModelScope.launch {
            usecase.getCryptoList()
                .catch { }
                .collect {
                    getCryptoList.value = it
                }
        }
    }

    fun getDollarPrice() = viewModelScope.launch {
        usecase.getDollarPrice()
            .catch {}
            .collect {
                getDollarPrice.value = it
            }
    }

}