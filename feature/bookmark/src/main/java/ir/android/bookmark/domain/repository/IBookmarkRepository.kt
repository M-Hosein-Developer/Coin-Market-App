package ir.android.bookmark.domain.repository

import ir.androidcoder.Model.CryptoCurrency
import kotlinx.coroutines.flow.Flow

interface IBookmarkRepository {
    fun getBookmarkList(): Flow<List<CryptoCurrency>>
    suspend fun addBookmark(item: CryptoCurrency)
    suspend fun removeBookmark(id: Int)
}