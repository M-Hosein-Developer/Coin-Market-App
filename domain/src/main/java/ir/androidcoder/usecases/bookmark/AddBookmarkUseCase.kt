package ir.androidcoder.usecases.bookmark

import ir.androidcoder.Model.CryptoCurrency
import ir.androidcoder.repository.IBookmarkRepository
import javax.inject.Inject

class AddBookmarkUseCase @Inject constructor(private val repository: IBookmarkRepository) {
    suspend operator fun invoke(item: CryptoCurrency) {
        repository.addBookmark(item)
    }
}