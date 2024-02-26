package com.example.coinmarket.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CoinEntity::class] , version = 1 , exportSchema = false)
abstract class MyDatabase : RoomDatabase(){

    abstract fun roomDao() : RoomDao

}