package ir.androidcoder.usecases.mainUsecase

import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import kotlinx.coroutines.flow.Flow

interface MainUsecase {

    fun getCryptoList() : Flow<List<CryptoCurrencyEntity>>

    fun getCryptoListFromDb() : Flow<List<CryptoCurrencyEntity>>

    fun getCryptoByIdFromDb(id : Int) : Flow<CryptoCurrencyEntity>

    fun getDollarPrice() : Flow<PriceEntity>

    fun insertBookmark(data : CryptoCurrencyEntity)

    fun getBookmarkList() : Flow<List<CryptoCurrencyEntity>>

    suspend fun deleteBookmark(id : Int)

}