package com.example.coinmarket.viewModel

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinmarket.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.entities.DynamicThemeEntity
import ir.androidcoder.usecases.themeUsecase.ThemeUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(private val usecase: ThemeUsecase , private val prefs: SharedPreferences) : ViewModel() {

    val themeState = MutableStateFlow(DynamicThemeEntity(1, false))
    var switchState by mutableStateOf(false)

    init {
       getDynamicThemeState()
    }

    fun insertDynamicThemeStateRep() {
        viewModelScope.launch(coroutineExceptionHandler) {
            usecase.insertDynamicThemeStateRep(
                DynamicThemeEntity(
                    1,
                    switchState
                )
            )
        }
    }

    fun getDynamicThemeState() = viewModelScope.launch {
        val entity = usecase.getDynamicThemeState() ?: DynamicThemeEntity(1 , false)
        themeState.value = entity
        switchState = entity.dynamicThemeState ?: false
    }

    fun saveLanguagePreference(language: String) {
        prefs.edit().putString("language", language).apply()
    }

    fun getSavedLanguage(): String =
        prefs.getString("language", "fa") ?: "fa"


}