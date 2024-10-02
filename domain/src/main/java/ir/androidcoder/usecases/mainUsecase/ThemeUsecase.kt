package ir.androidcoder.usecases.mainUsecase

import ir.androidcoder.entities.DynamicThemeEntity

interface ThemeUsecase {

    suspend fun insertDynamicThemeStateRep(state : DynamicThemeEntity)

    suspend fun getDynamicThemeState() : DynamicThemeEntity

}