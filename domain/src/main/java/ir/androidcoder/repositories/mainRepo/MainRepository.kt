package ir.androidcoder.repositories.mainRepo

import androidx.paging.PagingData
import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import kotlinx.coroutines.flow.Flow


interface MainRepository {

    fun getCryptoList() : Flow<PagingData<CryptoCurrencyEntity>>

    fun getCryptoByIdFromDb(id : Int) : Flow<CryptoCurrencyEntity>

    fun getDollarPrice() : Flow<PriceEntity>

    suspend fun insertBookmark(data : CryptoCurrencyEntity)

    fun getBookmarkList() : Flow<List<CryptoCurrencyEntity>>

    suspend fun deleteBookmark(id : Int)


}