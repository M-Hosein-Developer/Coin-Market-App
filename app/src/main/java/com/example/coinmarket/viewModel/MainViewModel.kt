package com.example.coinmarket.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.model.repository.MainRepository
import com.example.coinmarket.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    init {
        getCryptoList {}
        getCryptoListFromDb {}
        getCryptoById(-1){}
    }

    fun getCryptoList(getCryptoList: (List<CoinMarketResponse.Data.CryptoCurrency>) -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.getCryptoList
                .catch { Log.v("error", "Error -> " + it.message) }
                .collect {
                    getCryptoList.invoke(it)
                }
        }
    }

    fun getCryptoListFromDb(getCryptoListFromDb: (List<CoinMarketResponse.Data.CryptoCurrency>) -> Unit) {

        viewModelScope.launch {
            repository.getCryptoListFromDb
                .catch { Log.v("errordb", "Error -> " + it.message) }
                .collect {
                    getCryptoListFromDb.invoke(it)
                }
        }

    }

    fun getCryptoById(id: Int, getCryptoById: (CoinMarketResponse.Data.CryptoCurrency) -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            while (true) {
                getCryptoById.invoke(repository.getCryptoByIdFromDb(id))
                delay(4000)
            }
        }
    }


}