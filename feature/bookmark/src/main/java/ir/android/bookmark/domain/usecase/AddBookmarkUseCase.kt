package ir.android.bookmark.domain.usecase

import ir.android.bookmark.domain.repository.IBookmarkRepository

javax.inject.Inject

class AddBookmarkUseCase @Inject constructor(private val repository: IBookmarkRepository) {
    suspend operator fun invoke(item: CryptoCurrency) {
        repository.addBookmark(item)
    }
}