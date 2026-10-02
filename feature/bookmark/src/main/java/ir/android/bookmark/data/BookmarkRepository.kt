package ir.android.bookmark.data

import ir.android.bookmark.domain.repository.IBookmarkRepository
import ir.androidcoder.Model.CryptoCurrency
import ir.androidcoder.local.RoomDao
import ir.androidcoder.mapper.toBookmarkEntity
import ir.androidcoder.mapper.toCryptoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val dao: RoomDao
) : IBookmarkRepository {
    override suspend fun addBookmark(item: CryptoCurrency) {
        dao.insertDataBookmark(item.toCryptoModel())
    }

    override fun getBookmarkList(): Flow<List<CryptoCurrency>> = flow {
        dao.getAlLBookmark().collect {
            emit(it.map { data -> data.toBookmarkEntity() })
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun removeBookmark(id: Int) {
        dao.deleteBookmarkListById(id)
    }
}