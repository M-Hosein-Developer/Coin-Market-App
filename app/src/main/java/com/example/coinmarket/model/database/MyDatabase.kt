package com.example.coinmarket.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coinmarket.model.dataClass.BookmarkResponse
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.model.dataClass.DynamicTheme
import com.example.coinmarket.model.database.converters.AuditInfoListConverter
import com.example.coinmarket.model.database.converters.IntegerListConverter
import com.example.coinmarket.model.database.converters.QuoteListConverter

@Database(entities = [CoinMarketResponse.Data.CryptoCurrency::class , DynamicTheme::class , BookmarkResponse.Data.CryptoCurrency::class] , version = 1 , exportSchema = false)
@TypeConverters(AuditInfoListConverter::class, IntegerListConverter::class, QuoteListConverter::class)
abstract class MyDatabase : RoomDatabase(){

    abstract fun roomDao() : RoomDao

}