package ir.androidcoder.repository

import ir.androidcoder.Model.CryptoCurrency
import ir.androidcoder.Model.Price
import kotlinx.coroutines.flow.Flow

interface IMainRepository {

    fun getCryptoList(): Flow<List<CryptoCurrency>>

    fun getDollarPrice(): Flow<Price>

}