package com.example.coinmarket.model.repository.themeRepo

import ir.androidcoder.local.dataClass.DynamicTheme

interface ThemeRepository {

    suspend fun insertDynamicThemeStateRep(state : ir.androidcoder.local.dataClass.DynamicTheme)

    suspend fun getDynamicThemeState() : ir.androidcoder.local.dataClass.DynamicTheme

}