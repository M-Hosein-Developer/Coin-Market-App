package ir.androidcoder.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.androidcoder.local.RoomDao
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.remote.ApiService
import ir.androidcoder.remote.ApiServicePrice
import ir.androidcoder.source.pagingMediator.CryptoPagingMediator
import ir.androidcoder.source.pagingSource.CryptoPagingSource
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

}