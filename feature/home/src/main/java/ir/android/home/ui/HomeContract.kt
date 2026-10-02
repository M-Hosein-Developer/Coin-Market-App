package ir.android.home.ui

import ir.androidcoder.Model.CryptoCurrency
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

object HomeContract {
    data class State(
        val isLoading: Boolean = true,
        val cryptoList: ImmutableList<CryptoCurrency> = persistentListOf()
    )

    sealed interface Action {
        data object BackClicked : Action
        data object SearchClicked : Action
        data class CryptoItemClicked(val id: Int) : Action
    }

    sealed interface Effect {
        data object NavBack : Effect
        data object NavToSearch : Effect
        data class NavToDetail(val id: Int) : Effect
    }
}