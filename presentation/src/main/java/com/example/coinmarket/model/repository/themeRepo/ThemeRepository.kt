package com.example.coinmarket.model.repository.themeRepo

import com.example.coinmarket.model.dataClass.DynamicTheme

interface ThemeRepository {

    suspend fun insertDynamicThemeStateRep(state : DynamicTheme)

    suspend fun getDynamicThemeState() : DynamicTheme

}