package com.example.coinmarket.model.database.converters

import androidx.room.TypeConverter
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AuditInfoListConverter {

    @TypeConverter
    fun fromString(value: String): List<CoinMarketResponse.Data.CryptoCurrency.AuditInfo> {
        val listType = object : TypeToken<List<CoinMarketResponse.Data.CryptoCurrency.AuditInfo>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<CoinMarketResponse.Data.CryptoCurrency.AuditInfo>): String {
        return Gson().toJson(list)
    }

}