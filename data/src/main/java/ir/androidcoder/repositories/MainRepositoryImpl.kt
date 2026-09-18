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

class MainRepositoryImpl @Inject constructor(private val source : CryptoSource, ) : MainRepository {

    override fun getCryptoListFormServer(): Flow<PagingData<CryptoCurrencyEntity>> = source.getCryptoListFormServer()
        .flow
        .map { pagingData -> pagingData.map { it.toCryptoEntity() } }

    override fun getCryptoListFormDatabase(query : String): Flow<PagingData<CryptoCurrencyEntity>> = source.getCryptoListFormDatabase(query)
        .flow
        .map { pagingData -> pagingData.map { it.toCryptoEntity() } }


    override fun getCryptoByIdFromDb(id: Int): Flow<CryptoCurrencyEntity> =
        source.getCryptoByIdFromDb(id).map { it.toCryptoEntity() }


    override fun getDollarPrice(): Flow<PriceEntity> =
        source.getDollarPrice().map { it.toPriceEntity() }


    //Bookmark
    override suspend fun insertBookmark(data: CryptoCurrencyEntity) {
        source.insertBookmark(data.toCryptoModel())
    }

    override fun getBookmarkList(): Flow<List<CryptoCurrencyEntity>> =
        source.getBookmarkList()

    override suspend fun deleteBookmark(id: Int) {
        source.deleteBookmark(id)
    }


}