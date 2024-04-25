package com.example.coinmarket.model.repository.mainRepo

import com.example.coinmarket.model.dataClass.BookmarkResponse
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.model.dataClass.PriceResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

//    suspend fun getCryptoList() : Flow<List<CoinMarketResponse.Data.CryptoCurrency>>

    val getCryptoList : Flow<List<CoinMarketResponse.Data.CryptoCurrency>>

    val getCryptoListFromDb : Flow<List<CoinMarketResponse.Data.CryptoCurrency>>

    suspend fun getCryptoByIdFromDb(id : Int) : CoinMarketResponse.Data.CryptoCurrency

    val getDollarPrice : Flow<PriceResponse>

    suspend fun insertBookmark(data : BookmarkResponse.Data.CryptoCurrency)

    val getBookmarkList : Flow<List<BookmarkResponse.Data.CryptoCurrency>>


}