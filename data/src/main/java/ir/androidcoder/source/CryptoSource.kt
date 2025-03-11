package ir.androidcoder.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.local.RoomDao
import ir.androidcoder.local.dataClass.BookmarkResponse
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.local.dataClass.PriceResponse
import ir.androidcoder.mapper.toBookmarkEntity
import ir.androidcoder.mapper.toCryptoEntity
import ir.androidcoder.mapper.toCryptoModel
import ir.androidcoder.mapper.toPriceEntity
import ir.androidcoder.remote.ApiService
import ir.androidcoder.remote.ApiServicePrice
import ir.androidcoder.source.pagingMediator.CryptoPagingMediator
import ir.androidcoder.source.pagingSource.CryptoPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CryptoSource @Inject constructor(private val apiService: ApiService, private val apiServicePrice: ApiServicePrice, private val dao: RoomDao) {


    fun getCryptoListFormServer() : Pager<Int , CoinMarketResponse.Data.CryptoCurrency> = Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CryptoPagingSource(apiService) }
        )

    fun getCryptoListFormDatabase(query : String) : Pager<Int , CoinMarketResponse.Data.CryptoCurrency> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        remoteMediator = CryptoPagingMediator(apiService , dao),
        pagingSourceFactory = {
            if (query == "") dao.getAlLCoinFromDb() else dao.getCoinByQuery(query)
        }
    )

    fun getCryptoByIdFromDb(id: Int): Flow<CoinMarketResponse.Data.CryptoCurrency> =
        dao.getCoinById(id)

    fun getDollarPrice(): Flow<PriceResponse> = flow {
        while (true){
            emit(apiServicePrice.getPriceDollar())
            delay(10000)
        }
    }.flowOn(Dispatchers.IO)

    //Bookmark
    suspend fun insertBookmark(data: BookmarkResponse.Data.CryptoCurrency) {
        dao.insertDataBookmark(data)
    }

    fun getBookmarkList(): Flow<List<CryptoCurrencyEntity>> = flow {
        dao.getAlLBookmark().collect{
            emit(it.map { data -> data.toBookmarkEntity() })
        }
    }.flowOn(Dispatchers.IO)

    suspend fun deleteBookmark(id: Int) {
        dao.deleteBookmarkListById(id)
    }

}