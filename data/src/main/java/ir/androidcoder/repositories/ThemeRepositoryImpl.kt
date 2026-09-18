package ir.androidcoder.repositories

import ir.androidcoder.entities.DynamicThemeEntity
import ir.androidcoder.mapper.toThemeEntity
import ir.androidcoder.mapper.toThemeModel
import ir.androidcoder.repositories.themeRepo.ThemeRepository
import ir.androidcoder.source.ThemeSource
import javax.inject.Inject


class ThemeRepositoryImpl @Inject constructor(private val source: ThemeSource) : ThemeRepository {


    override suspend fun insertDynamicThemeStateRep(state: DynamicThemeEntity) =
        source.insertDynamicThemeStateRep(state.toThemeModel())

    override suspend fun getDynamicThemeState():
            DynamicThemeEntity = source.getDynamicThemeState().toThemeEntity()


}