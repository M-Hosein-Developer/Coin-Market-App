package ir.androidcoder.usecases.mainUsecase

import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.repositories.mainRepo.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainUsecaseImpl(private val repository : MainRepository) {

    fun getCryptoList() = repository.getCryptoList()

    fun getCryptoListFromDb() = repository.getCryptoListFromDb()

    fun getCryptoByIdFromDb(id: Int) = repository.getCryptoByIdFromDb(id)

    fun getDollarPrice() = repository.getDollarPrice()

    val getBookmarkList: Flow<List<CryptoCurrencyEntity>> = repository.getBookmarkList.map { it }


    suspend fun insertBookmark(data: CryptoCurrencyEntity) {
        repository.insertBookmark(data)
    }

    suspend fun deleteBookmark(id: Int) {
        repository.deleteBookmark(id)
    }


}