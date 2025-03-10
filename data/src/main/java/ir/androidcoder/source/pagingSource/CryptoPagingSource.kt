package ir.androidcoder.source.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.remote.ApiService
import kotlinx.coroutines.delay

class CryptoPagingSource(private val api : ApiService) :PagingSource<Int , CoinMarketResponse.Data.CryptoCurrency>(){
    override fun getRefreshKey(state: PagingState<Int, CoinMarketResponse.Data.CryptoCurrency>): Int? = state.anchorPosition?.let { anchor ->
        state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinMarketResponse.Data.CryptoCurrency> = try {
        val currentPage = params.key ?: 1
        val response = api.getCryptoList(start = currentPage * 20 - 20 + 1, limit = currentPage * 20).data.cryptoCurrencyList
        delay(1500)

        LoadResult.Page(
            data = response,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (response.isEmpty()) null else currentPage + 1
        )
    } catch (e : Exception){
        LoadResult.Error(e)
    }
}