package ir.androidcoder.repositories.themeRepo

import ir.androidcoder.entities.DynamicThemeEntity

interface ThemeRepository {

    suspend fun insertDynamicThemeStateRep(state : DynamicThemeEntity)

    suspend fun getDynamicThemeState() : DynamicThemeEntity

}