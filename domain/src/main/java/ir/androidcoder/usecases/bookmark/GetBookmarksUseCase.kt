package ir.androidcoder.usecases.bookmark

import ir.androidcoder.Model.CryptoCurrency
import ir.androidcoder.repository.IBookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(private val repository: IBookmarkRepository) {
    operator fun invoke(): Flow<List<CryptoCurrency>> {
        return repository.getBookmarkList()
    }
}