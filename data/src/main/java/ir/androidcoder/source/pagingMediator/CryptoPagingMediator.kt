package ir.androidcoder.source.pagingMediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ir.androidcoder.local.RoomDao
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.remote.ApiService
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagingApi::class)
class CryptoPagingMediator(private val api : ApiService , private val dao: RoomDao) : RemoteMediator<Int, CoinMarketResponse.Data.CryptoCurrency>() {

    private var currentPage = 1


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinMarketResponse.Data.CryptoCurrency>
    ): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    currentPage += 1
                    currentPage * 20
                }
            }

            val response = api.getCryptoList().data.cryptoCurrencyList
            dao.insertDataFrom(response)
            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

}