package com.example.coinmarket.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.model.dataClass.DynamicTheme

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataFrom(data: List<CoinMarketResponse.Data.CryptoCurrency>)

    @Query("SELECT * FROM CryptoCurrency")
    suspend fun getAlLCoinFromDb(): List<CoinMarketResponse.Data.CryptoCurrency>

    @Query("SELECT * FROM CryptoCurrency WHERE id = :id ")
    suspend fun getCoinById(id : Int) : CoinMarketResponse.Data.CryptoCurrency

    //Dynamic Theme
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDynamicTheme(state : DynamicTheme)

    @Query("SELECT * FROM DynamicTheme")
    suspend fun getDynamicThemeState() : DynamicTheme



}