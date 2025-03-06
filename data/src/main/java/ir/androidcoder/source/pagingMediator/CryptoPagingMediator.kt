package ir.androidcoder.source.pagingMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.local.RoomDao
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.remote.ApiService

@OptIn(ExperimentalPagingApi::class)
class CryptoPagingMediator(private val api : ApiService , private val dao: RoomDao) : RemoteMediator<Int, CoinMarketResponse.Data.CryptoCurrency>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinMarketResponse.Data.CryptoCurrency>
    ): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id?.plus(1) ?: 1
                }
            }

            val response = api.getCryptoList(limit = page * 10).data.cryptoCurrencyList
            dao.insertDataFrom(response)
            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

}