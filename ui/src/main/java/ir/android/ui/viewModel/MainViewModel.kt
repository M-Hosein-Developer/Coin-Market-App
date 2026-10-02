package ir.android.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MainViewModel<UiState, UiAction, SideEffect>(initialUiState: UiState) :
    ViewModel() {

    private val mutableUiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<UiState> = mutableUiState.asStateFlow()
    val currentState: UiState get() = uiState.value

    private val mutableSideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = mutableSideEffect.receiveAsFlow()

    protected fun updateState(reducer: UiState.() -> UiState) {
        mutableUiState.update(reducer)
    }

    protected fun triggerEffect(effect: SideEffect) {
        viewModelScope.launch { mutableSideEffect.send(effect) }
    }

    abstract fun onAction(action: UiAction)
}