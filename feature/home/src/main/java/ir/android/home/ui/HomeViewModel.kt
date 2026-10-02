package ir.android.home.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.android.home.domain.GetCryptoListUsecase
import ir.android.ui.viewModel.MainViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCryptoListUsecase: GetCryptoListUsecase
) : MainViewModel<HomeContract.State, HomeContract.Action, HomeContract.Effect>(
    HomeContract.State()
) {

    override fun onAction(action: HomeContract.Action) {
        when (action) {
            is HomeContract.Action.BackClicked -> triggerEffect(HomeContract.Effect.NavBack)
            is HomeContract.Action.SearchClicked -> triggerEffect(HomeContract.Effect.NavToSearch)
            is HomeContract.Action.CryptoItemClicked -> triggerEffect(
                HomeContract.Effect.NavToDetail(
                    action.id
                )
            )
        }
    }

    fun onLaunch() {
        getCryptoList()
    }

    private fun getCryptoList() {
        viewModelScope.launch {
            while (true) {
                getCryptoListUsecase()
                    .catch { }
                    .collect {
                        updateState { copy(cryptoList = it.toImmutableList()) }
                    }
                delay(5000.milliseconds)
            }
        }
    }
}