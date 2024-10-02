package ir.androidcoder.usecases.themeUsecase

import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import kotlinx.coroutines.flow.Flow

interface MainUsecase {

    val getCryptoList : Flow<List<CryptoCurrencyEntity>>

    val getCryptoListFromDb : Flow<List<CryptoCurrencyEntity>>

    suspend fun getCryptoByIdFromDb(id : Int) : CryptoCurrencyEntity

    val getDollarPrice : Flow<PriceEntity>

    suspend fun insertBookmark(data : CryptoCurrencyEntity)

    val getBookmarkList : Flow<List<CryptoCurrencyEntity>>

    suspend fun deleteBookmark(id : Int)

}