package ir.androidcoder.repositories.mainRepo

import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import kotlinx.coroutines.flow.Flow


interface MainRepository {


    fun getCryptoList() : Flow<List<CryptoCurrencyEntity>>

    fun getCryptoListFromDb() : Flow<List<CryptoCurrencyEntity>>

    fun getCryptoByIdFromDb(id : Int) : Flow<CryptoCurrencyEntity>

    val getDollarPrice : Flow<PriceEntity>

    suspend fun insertBookmark(data : CryptoCurrencyEntity)

    val getBookmarkList : Flow<List<CryptoCurrencyEntity>>

    suspend fun deleteBookmark(id : Int)


}