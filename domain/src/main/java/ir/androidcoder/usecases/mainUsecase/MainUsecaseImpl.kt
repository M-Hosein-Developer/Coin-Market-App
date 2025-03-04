package ir.androidcoder.usecases.mainUsecase

import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.repositories.mainRepo.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainUsecaseImpl(private val repository : MainRepository) : MainUsecase {

    override fun getCryptoList() = repository.getCryptoList()

    override fun getCryptoListFromDb() = repository.getCryptoListFromDb()

    override fun getCryptoByIdFromDb(id: Int) = repository.getCryptoByIdFromDb(id)

    override fun getDollarPrice() = repository.getDollarPrice()

    override fun getBookmarkList() = repository.getBookmarkList()


    override fun insertBookmark(data: CryptoCurrencyEntity) {
        repository.insertBookmark(data)
    }

    override suspend fun deleteBookmark(id: Int) {
        repository.deleteBookmark(id)
    }


}