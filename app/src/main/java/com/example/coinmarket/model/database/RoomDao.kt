package com.example.coinmarket.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataFrom(data : List<CoinEntity>)

    @Query("SELECT * FROM CoinEntity")
    suspend fun getAlLCOinFromDb(): List<CoinEntity>

    @Query("SELECT * FROM CoinEntity WHERE id = :id ")
    suspend fun getCoinById(id : String) : CoinEntity

}