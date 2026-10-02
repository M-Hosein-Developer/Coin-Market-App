package ir.androidcoder.local.converters.bookmark

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.androidcoder.model.BookmarkResponseDto

class QuoteListBookConverter {

    @TypeConverter
    fun fromString(value: String): List<BookmarkResponseDto.Data.CryptoCurrency.Quote> {
        val listType =
            object : TypeToken<List<BookmarkResponseDto.Data.CryptoCurrency.Quote>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<BookmarkResponseDto.Data.CryptoCurrency.Quote>): String {
        return Gson().toJson(list)
    }

}