package com.example.coinmarket.model.database.converters.bookmark

import androidx.room.TypeConverter
import com.example.coinmarket.model.dataClass.BookmarkResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AuditInfoListBookConverter {

    @TypeConverter
    fun fromString(value: String): List<BookmarkResponse.Data.CryptoCurrency.AuditInfo> {
        val listType = object : TypeToken<List<BookmarkResponse.Data.CryptoCurrency.AuditInfo>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<BookmarkResponse.Data.CryptoCurrency.AuditInfo>): String {
        return Gson().toJson(list)
    }

}