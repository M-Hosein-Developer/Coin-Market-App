package com.example.coinmarket.model.repository.mainRepo

import ir.androidcoder.local.dataClass.BookmarkResponse
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.local.dataClass.PriceResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

//    suspend fun getCryptoList() : Flow<List<CoinMarketResponse.Data.CryptoCurrency>>

    val getCryptoList : Flow<List<ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency>>

    val getCryptoListFromDb : Flow<List<ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency>>

    suspend fun getCryptoByIdFromDb(id : Int) : ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency

    val getDollarPrice : Flow<ir.androidcoder.local.dataClass.PriceResponse>

    suspend fun insertBookmark(data : ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency)

    val getBookmarkList : Flow<List<ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency>>

    suspend fun deleteBookmark(id : Int)


}