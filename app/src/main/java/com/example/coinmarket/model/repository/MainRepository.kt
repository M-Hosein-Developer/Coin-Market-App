package com.example.coinmarket.model.repository

import com.example.coinmarket.model.dataClass.CoinMarketResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

//    suspend fun getCryptoList() : Flow<List<CoinMarketResponse.Data.CryptoCurrency>>

    val getCryptoList : Flow<List<CoinMarketResponse.Data.CryptoCurrency>>
}