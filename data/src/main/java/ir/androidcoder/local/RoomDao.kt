package ir.androidcoder.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.androidcoder.local.dataClass.BookmarkResponse
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.local.dataClass.DynamicTheme

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

    //Bookmark
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataBookmark(data: BookmarkResponse.Data.CryptoCurrency)

    @Query("SELECT * FROM bookmarkTable")
    suspend fun getAlLBookmark(): List<BookmarkResponse.Data.CryptoCurrency>

    @Query("DELETE FROM bookmarkTable WHERE id = :id")
    suspend fun deleteBookmarkListById( id : Int)


}