package ir.android.bookmark.domain.usecase

import ir.android.bookmark.domain.repository.IBookmarkRepository
import ir.androidcoder.Model.CryptoCurrency
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(private val repository: IBookmarkRepository) {
    operator fun invoke(): Flow<List<CryptoCurrency>> {
        return repository.getBookmarkList()
    }
}