package ir.android.bookmark.ui

import ir.androidcoder.Model.CryptoCurrency
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

object BookmarkContract {

    data class State(
        val isLoading: Boolean = false,
        val bookmarkList: ImmutableList<CryptoCurrency> = persistentListOf()
    )

    sealed interface Action {
        data class BookmarkCLicked(val item: CryptoCurrency) : Action
        data class UnBookmarkCLicked(val id: Int) : Action
        data class DetailClicked(val id: Int) : Action
        data object BackClicked : Action
    }

    sealed interface Effect {
        data object NavBack : Effect
        data class NavToDetail(val id: Int) : Effect
    }
}
