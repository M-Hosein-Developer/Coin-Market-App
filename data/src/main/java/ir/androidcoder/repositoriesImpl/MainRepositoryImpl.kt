package ir.androidcoder.repositoriesImpl

import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.local.RoomDao
import ir.androidcoder.mapper.toBookmarkEntity
import ir.androidcoder.mapper.toCryptoEntity
import ir.androidcoder.mapper.toCryptoModel
import ir.androidcoder.mapper.toPriceEntity
import ir.androidcoder.remote.ApiService
import ir.androidcoder.remote.ApiServicePrice
import ir.androidcoder.repositories.mainRepo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiServicePrice: ApiServicePrice,
    private val dao: RoomDao
) : MainRepository {

    override fun getCryptoList(): Flow<List<CryptoCurrencyEntity>> = flow {
        while (true) {
            val data = apiService.getCryptoList().data.cryptoCurrencyList
            emit(data.map { it.toCryptoEntity() })
            dao.insertDataFrom(data)
            delay(3000)
        }
    }

    override fun getCryptoListFromDb(): Flow<List<CryptoCurrencyEntity>> = flow {
        while (true) {
            val data = dao.getAlLCoinFromDb()
            emit(data.map { it.toCryptoEntity() })
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)

    override fun getCryptoByIdFromDb(id: Int): Flow<CryptoCurrencyEntity> = dao.getCoinById(id).map { it.toCryptoEntity() }

    override fun getDollarPrice(): Flow<PriceEntity> = flow {
        while (true){
            emit(apiServicePrice.getPriceDollar().toPriceEntity())
            delay(10000)
        }
    }.flowOn(Dispatchers.IO)


    //Bookmark
    override suspend fun insertBookmark(data: CryptoCurrencyEntity) {
        dao.insertDataBookmark(data.toCryptoModel())
    }

    override val getBookmarkList: Flow<List<CryptoCurrencyEntity>> = flow {
        while (true){
            emit(dao.getAlLBookmark().map { it.toBookmarkEntity() })
            delay(5000)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteBookmark(id: Int) {
        dao.deleteBookmarkListById(id)
    }


}