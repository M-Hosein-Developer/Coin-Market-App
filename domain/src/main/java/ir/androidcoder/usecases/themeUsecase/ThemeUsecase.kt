package ir.androidcoder.usecases.themeUsecase

import ir.androidcoder.entities.DynamicThemeEntity

interface ThemeUsecase {

    suspend fun insertDynamicThemeStateRep(state : DynamicThemeEntity)

    suspend fun getDynamicThemeState() : DynamicThemeEntity

}