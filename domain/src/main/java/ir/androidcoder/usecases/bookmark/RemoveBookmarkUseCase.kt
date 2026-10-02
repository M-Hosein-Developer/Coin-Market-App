package ir.androidcoder.usecases.bookmark

import ir.androidcoder.repository.IBookmarkRepository
import javax.inject.Inject

class RemoveBookmarkUseCase @Inject constructor(private val repository: IBookmarkRepository) {
    suspend operator fun invoke(id: Int) {
        repository.removeBookmark(id)
    }
}