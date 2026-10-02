package ir.android.bookmark.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.android.bookmark.domain.usecase.AddBookmarkUseCase
import ir.android.bookmark.domain.usecase.GetBookmarksUseCase
import ir.android.bookmark.domain.usecase.RemoveBookmarkUseCase
import ir.android.ui.viewModel.MainViewModel
import ir.androidcoder.Model.CryptoCurrency
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase
) : MainViewModel<BookmarkContract.State, BookmarkContract.Action, BookmarkContract.Effect>(
    BookmarkContract.State()
) {

    override fun onAction(action: BookmarkContract.Action) {
        when (action) {
            is BookmarkContract.Action.BookmarkCLicked -> onBookmarkClick(action.item)
            is BookmarkContract.Action.UnBookmarkCLicked -> onUnBookmarkClick(action.id)
            is BookmarkContract.Action.BackClicked -> triggerEffect(BookmarkContract.Effect.NavBack)
            is BookmarkContract.Action.DetailClicked -> triggerEffect(
                BookmarkContract.Effect.NavToDetail(
                    action.id
                )
            )
        }
    }

    fun onLaunch() {
        getCryptoBookmarkList()
    }

    private fun getCryptoBookmarkList() {
        viewModelScope.launch {
            getBookmarksUseCase()
                .catch {}
                .collect {
                    updateState { BookmarkContract.State(bookmarkList = it.toImmutableList()) }
                }
        }
    }

    private fun onBookmarkClick(data: CryptoCurrency) {
        viewModelScope.launch {
            addBookmarkUseCase(data)
        }
    }

    private fun onUnBookmarkClick(id: Int) {
        viewModelScope.launch {
            removeBookmarkUseCase(id)
        }
    }
}