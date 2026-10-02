package ir.androidcoder.repository

import ir.androidcoder.Model.DynamicTheme

interface IThemeRepository {

    suspend fun insertDynamicThemeStateRep(state: DynamicTheme)

    suspend fun getDynamicThemeState(): DynamicTheme

}