package ir.androidcoder.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coinmarket.model.dataClass.BookmarkResponse
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.model.dataClass.DynamicTheme
import ir.androidcoder.local.converters.AuditInfoListConverter
import ir.androidcoder.local.converters.IntegerListConverter
import ir.androidcoder.local.converters.QuoteListConverter
import ir.androidcoder.local.converters.bookmark.AuditInfoListBookConverter
import ir.androidcoder.local.converters.bookmark.QuoteListBookConverter

@Database(entities = [CoinMarketResponse.Data.CryptoCurrency::class , DynamicTheme::class , BookmarkResponse.Data.CryptoCurrency::class] , version = 1 , exportSchema = false)
@TypeConverters(
    AuditInfoListConverter::class,
    IntegerListConverter::class,
    QuoteListConverter::class ,
    AuditInfoListBookConverter::class,
    QuoteListBookConverter::class
)
abstract class MyDatabase : RoomDatabase(){

    abstract fun roomDao() : RoomDao

}