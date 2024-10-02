package ir.androidcoder.usecases.mainUsecase

import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.repositories.mainRepo.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainUsecaseImpl(private val repository : MainRepository) : MainUsecase {

    override val getCryptoList: Flow<List<CryptoCurrencyEntity>> = repository.getCryptoList.map { it }

    override val getCryptoListFromDb: Flow<List<CryptoCurrencyEntity>> = repository.getCryptoListFromDb.map { it }

    override suspend fun getCryptoByIdFromDb(id: Int): CryptoCurrencyEntity = repository.getCryptoByIdFromDb(id)

    override val getDollarPrice: Flow<PriceEntity> = repository.getDollarPrice.map { it }

    override val getBookmarkList: Flow<List<CryptoCurrencyEntity>> = repository.getBookmarkList.map { it }


    override suspend fun insertBookmark(data: CryptoCurrencyEntity) {
        repository.insertBookmark(data)
    }

    override suspend fun deleteBookmark(id: Int) {
        repository.deleteBookmark(id)
    }


}