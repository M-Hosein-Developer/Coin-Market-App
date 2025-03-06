package ir.androidcoder.repositories

import androidx.paging.PagingData
import androidx.paging.map
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
import ir.androidcoder.source.CryptoSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val source : CryptoSource,
    private val apiService: ApiService,
    private val apiServicePrice: ApiServicePrice,
    private val dao: RoomDao
) : MainRepository {

    override fun getCryptoList(): Flow<PagingData<CryptoCurrencyEntity>> = source.getCryptoList()
        .flow
        .map { pagingData -> pagingData.map { it.toCryptoEntity() } }


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

    override fun getBookmarkList(): Flow<List<CryptoCurrencyEntity>> = flow {
        dao.getAlLBookmark().collect{
            emit(it.map { data -> data.toBookmarkEntity() })
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteBookmark(id: Int) {
        dao.deleteBookmarkListById(id)
    }


}