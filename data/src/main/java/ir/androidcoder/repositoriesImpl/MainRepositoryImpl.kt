package ir.androidcoder.repositoriesImpl

import android.util.Log
import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.mapper.toBookmarkEntity
import ir.androidcoder.mapper.toCryptoEntity
import ir.androidcoder.mapper.toCryptoModel
import ir.androidcoder.mapper.toPriceEntity
import ir.androidcoder.repositories.mainRepo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepositoryImpl (private val apiService: ir.androidcoder.remote.ApiService, private val apiServicePrice: ir.androidcoder.remote.ApiServicePrice, private val dao: ir.androidcoder.local.RoomDao) :
    MainRepository {

    override val getCryptoList: Flow<List<CryptoCurrencyEntity>> = flow {
        while (true) {
            val data = apiService.getCryptoList().data.cryptoCurrencyList
            emit(data.map { it.toCryptoEntity() })
            dao.insertDataFrom(data)
            delay(3000)
            Log.v("testData", data.toString())
        }
    }.flowOn(Dispatchers.IO)

    override val getCryptoListFromDb: Flow<List<CryptoCurrencyEntity>> = flow {
        while (true) {
            val data = dao.getAlLCoinFromDb()
            emit(data.map { it.toCryptoEntity() })
            delay(1000)
            Log.v("testDataFromDb", data.toString())

        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCryptoByIdFromDb(id: Int): CryptoCurrencyEntity = dao.getCoinById(id).toCryptoEntity()

    override val getDollarPrice: Flow<PriceEntity> = flow {
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