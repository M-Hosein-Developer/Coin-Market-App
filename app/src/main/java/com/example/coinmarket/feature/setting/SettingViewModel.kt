package com.example.coinmarket.feature.setting

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinmarket.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.Model.DynamicTheme
import ir.androidcoder.usecases.theme.ThemeUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val usecase: ThemeUsecase,
    private val prefs: SharedPreferences
) : ViewModel() {

    val themeState = MutableStateFlow(DynamicTheme(1, false))
    var switchState by mutableStateOf(false)

    init {
       getDynamicThemeState()
    }

    fun insertDynamicThemeStateRep() {
        viewModelScope.launch(coroutineExceptionHandler) {
            usecase.insertDynamicThemeStateRep(
                DynamicTheme(
                    1,
                    switchState
                )
            )
        }
    }

    fun getDynamicThemeState() = viewModelScope.launch {
        val entity = usecase.getDynamicThemeState() ?: DynamicTheme(1, false)
        themeState.value = entity
        switchState = entity.dynamicThemeState
    }

    fun saveLanguagePreference(language: String) {
        prefs.edit().putString("language", language).apply()
    }

    fun getSavedLanguage(): String =
        prefs.getString("language", "fa") ?: "fa"


}