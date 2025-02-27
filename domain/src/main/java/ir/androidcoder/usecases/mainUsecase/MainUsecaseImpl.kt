package ir.androidcoder.usecases.mainUsecase

import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.repositories.mainRepo.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainUsecaseImpl(private val repository : MainRepository) {

    val getCryptoList: Flow<List<CryptoCurrencyEntity>> = repository.getCryptoList.map { it }

    val getCryptoListFromDb: Flow<List<CryptoCurrencyEntity>> = repository.getCryptoListFromDb.map { it }

    suspend fun getCryptoByIdFromDb(id: Int): CryptoCurrencyEntity = repository.getCryptoByIdFromDb(id)

    val getDollarPrice: Flow<PriceEntity> = repository.getDollarPrice.map { it }

    val getBookmarkList: Flow<List<CryptoCurrencyEntity>> = repository.getBookmarkList.map { it }


    suspend fun insertBookmark(data: CryptoCurrencyEntity) {
        repository.insertBookmark(data)
    }

    suspend fun deleteBookmark(id: Int) {
        repository.deleteBookmark(id)
    }


}