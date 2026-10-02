package ir.android.bookmark.domain.usecase

import ir.android.bookmark.domain.repository.IBookmarkRepository
import javax.inject.Inject

class RemoveBookmarkUseCase @Inject constructor(private val repository: IBookmarkRepository) {
    suspend operator fun invoke(id: Int) {
        repository.removeBookmark(id)
    }
}