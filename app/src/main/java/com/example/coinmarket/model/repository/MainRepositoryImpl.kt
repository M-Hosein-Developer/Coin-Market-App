package com.example.coinmarket.model.repository

import android.util.Log
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.model.remote.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService) : MainRepository {

    override val getCryptoList : Flow<List<CoinMarketResponse.Data.CryptoCurrency>> = flow {
        while (true){
            val data = apiService.getCryptoList().data.cryptoCurrencyList
            emit(data)
            delay(3000)
            Log.v("testData",data.toString())
        }
    }

}