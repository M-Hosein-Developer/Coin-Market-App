package com.example.coinmarket.model.repository.mainRepo

import android.util.Log
import ir.androidcoder.local.dataClass.BookmarkResponse
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.local.dataClass.PriceResponse
import ir.androidcoder.local.RoomDao
import ir.androidcoder.remote.ApiService
import ir.androidcoder.remote.ApiServicePrice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ir.androidcoder.remote.ApiService, private val apiServicePrice: ir.androidcoder.remote.ApiServicePrice, private val dao: ir.androidcoder.local.RoomDao) :
    MainRepository {

    override val getCryptoList: Flow<List<ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency>> = flow {
        while (true) {
            val data = apiService.getCryptoList().data.cryptoCurrencyList
            emit(data)
            dao.insertDataFrom(data)
            delay(3000)
            Log.v("testData", data.toString())
        }
    }.flowOn(Dispatchers.IO)

    override val getCryptoListFromDb: Flow<List<ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency>> = flow {
        while (true) {
            val data = dao.getAlLCoinFromDb()
            emit(data)
            delay(1000)
            Log.v("testDataFromDb", data.toString())

        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCryptoByIdFromDb(id: Int): ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency = dao.getCoinById(id)

    override val getDollarPrice: Flow<ir.androidcoder.local.dataClass.PriceResponse> = flow {
        while (true){
            emit(apiServicePrice.getPriceDollar())
            delay(10000)
        }
    }.flowOn(Dispatchers.IO)


    //Bookmark
    override suspend fun insertBookmark(data: ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency) {
        dao.insertDataBookmark(data)
    }

    override val getBookmarkList: Flow<List<ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency>> = flow {
        while (true){
            emit(dao.getAlLBookmark())
            delay(5000)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteBookmark(id: Int) {
        dao.deleteBookmarkListById(id)
    }


}