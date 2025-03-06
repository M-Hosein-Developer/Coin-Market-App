package ir.androidcoder.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.androidcoder.local.RoomDao
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.remote.ApiService
import ir.androidcoder.remote.ApiServicePrice
import ir.androidcoder.source.pagingMediator.CryptoPagingMediator
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CryptoSource @Inject constructor(private val apiService: ApiService, private val apiServicePrice: ApiServicePrice, private val dao: RoomDao) {


    fun getCryptoList() : Pager<Int , CoinMarketResponse.Data.CryptoCurrency> = Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = CryptoPagingMediator(api = apiService , dao = dao),
            pagingSourceFactory = { dao.getAlLCoinFromDb() }
        )

}