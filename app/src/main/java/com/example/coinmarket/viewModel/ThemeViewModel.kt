package com.example.coinmarket.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinmarket.model.dataClass.DynamicTheme
import com.example.coinmarket.model.repository.themeRepo.ThemeRepository
import com.example.coinmarket.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(private val repository: ThemeRepository) : ViewModel() {

    var themeState by mutableStateOf(DynamicTheme(1, false))
    var switchState by mutableStateOf(false)

    init {
        getDynamicThemeState()
    }

    fun insertDynamicThemeStateRep() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.insertDynamicThemeStateRep(DynamicTheme(1, switchState))
        }
    }

    private fun getDynamicThemeState() = viewModelScope.launch {
        while (true) {
            themeState = repository.getDynamicThemeState()
            switchState = if (repository.getDynamicThemeState().dynamicThemeState)
                true
            else
                false
            delay(100)
        }

    }


}