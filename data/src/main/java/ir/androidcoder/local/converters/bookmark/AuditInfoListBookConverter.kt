package ir.androidcoder.local.converters.bookmark

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.androidcoder.local.dataClass.BookmarkResponse

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