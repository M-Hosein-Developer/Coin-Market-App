package ir.androidcoder.repositories

import ir.androidcoder.entities.DynamicThemeEntity
import ir.androidcoder.mapper.toThemeEntity
import ir.androidcoder.mapper.toThemeModel
import ir.androidcoder.repositories.themeRepo.ThemeRepository


class ThemeRepositoryImpl (private val myDao: ir.androidcoder.local.RoomDao) : ThemeRepository {


    override suspend fun insertDynamicThemeStateRep(state: DynamicThemeEntity) = myDao.insertDynamicTheme(state.toThemeModel())

    override suspend fun getDynamicThemeState(): DynamicThemeEntity = myDao.getDynamicThemeState().toThemeEntity()


}