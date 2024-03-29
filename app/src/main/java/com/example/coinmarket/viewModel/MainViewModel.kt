package com.example.coinmarket.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.model.repository.MainRepository
import com.example.coinmarket.util.EmptyCoinList
import com.example.coinmarket.util.NetworkChecker
import com.example.coinmarket.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository , context: Context) : ViewModel() {

    val getCryptoList = mutableStateOf(EmptyCoinList)
    val search = mutableStateOf("")

    init {
        if (NetworkChecker(context).internetConnection){
            getCryptoList()
        }else{
            getCryptoListFromDb()
        }
        getCryptoById(-1){}
    }

    private fun getCryptoList() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.getCryptoList
                .catch { Log.v("error", "Error -> " + it.message) }
                .collect {
                    getCryptoList.value = it
                    Log.v("testDataFromVar1" , it.toString())
                }
        }
    }

    private fun getCryptoListFromDb() {

        viewModelScope.launch {
            repository.getCryptoListFromDb
                .catch { Log.v("errorDb", "Error -> " + it.message) }
                .collect {
                    getCryptoList.value = it
                }
        }

    }

    fun getCryptoById(id: Int , getCryptoById :(CoinMarketResponse.Data.CryptoCurrency) -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            while (true) {
                getCryptoById.invoke(repository.getCryptoByIdFromDb(id))
                delay(4000)
            }
        }
    }


}