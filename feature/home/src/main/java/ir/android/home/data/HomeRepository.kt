package ir.android.home.data

import ir.androidcoder.Model.CryptoCurrency
import ir.androidcoder.local.RoomDao
import ir.androidcoder.mapper.toCryptoEntity
import ir.androidcoder.remote.ApiServicePrice
import ir.androidcoder.remote.HomeApiService
import ir.androidcoder.repository.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class HomeRepository @Inject constructor(
    private val homeApiService: HomeApiService,
    private val apiServicePrice: ApiServicePrice,
    private val dao: RoomDao
) : IHomeRepository {

    override fun getCryptoList(): Flow<List<CryptoCurrency>> = flow {
        while (true) {
            val data = homeApiService.getCryptoList().data.cryptoCurrencyList
            emit(data.map { it.toCryptoEntity() })
            delay(10000.milliseconds)
        }
    }.flowOn(Dispatchers.IO)

    /*  override fun getDollarPrice(): Flow<Price> = flow {
          while (true) {
              emit(apiServicePrice.getPriceDollar().toPriceEntity())
              delay(60000.milliseconds)
          }
      }.flowOn(Dispatchers.IO)*/

}