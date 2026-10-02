package ir.android.home.domain

import ir.androidcoder.Model.CryptoCurrency
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    fun getCryptoList(): Flow<List<CryptoCurrency>>

}