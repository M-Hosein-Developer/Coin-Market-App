package com.example.coinmarket.model.database.converters

import androidx.room.TypeConverter
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuoteListConverter {

    @TypeConverter
    fun fromString(value: String): List<CoinMarketResponse.Data.CryptoCurrency.Quote> {
        val listType = object : TypeToken<List<CoinMarketResponse.Data.CryptoCurrency.Quote>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<CoinMarketResponse.Data.CryptoCurrency.Quote>): String {
        return Gson().toJson(list)
    }

}