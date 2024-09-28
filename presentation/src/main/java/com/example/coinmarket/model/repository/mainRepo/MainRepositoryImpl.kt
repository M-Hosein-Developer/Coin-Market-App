package com.example.coinmarket.model.repository.mainRepo

import android.util.Log
import com.example.coinmarket.model.dataClass.BookmarkResponse
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.model.dataClass.PriceResponse
import com.example.coinmarket.model.database.RoomDao
import com.example.coinmarket.model.remote.ApiService
import com.example.coinmarket.model.remote.ApiServicePrice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService, private val apiServicePrice: ApiServicePrice, private val dao: RoomDao) :
    MainRepository {

    override val getCryptoList: Flow<List<CoinMarketResponse.Data.CryptoCurrency>> = flow {
        while (true) {
            val data = apiService.getCryptoList().data.cryptoCurrencyList
            emit(data)
            dao.insertDataFrom(data)
            delay(3000)
            Log.v("testData", data.toString())
        }
    }.flowOn(Dispatchers.IO)

    override val getCryptoListFromDb: Flow<List<CoinMarketResponse.Data.CryptoCurrency>> = flow {
        while (true) {
            val data = dao.getAlLCoinFromDb()
            emit(data)
            delay(1000)
            Log.v("testDataFromDb", data.toString())

        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCryptoByIdFromDb(id: Int): CoinMarketResponse.Data.CryptoCurrency = dao.getCoinById(id)

    override val getDollarPrice: Flow<PriceResponse> = flow {
        while (true){
            emit(apiServicePrice.getPriceDollar())
            delay(10000)
        }
    }.flowOn(Dispatchers.IO)


    //Bookmark
    override suspend fun insertBookmark(data: BookmarkResponse.Data.CryptoCurrency) {
        dao.insertDataBookmark(data)
    }

    override val getBookmarkList: Flow<List<BookmarkResponse.Data.CryptoCurrency>> = flow {
        while (true){
            emit(dao.getAlLBookmark())
            delay(5000)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteBookmark(id: Int) {
        dao.deleteBookmarkListById(id)
    }


}