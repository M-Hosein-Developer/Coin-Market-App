package ir.androidcoder.source

import ir.androidcoder.entities.DynamicThemeEntity
import ir.androidcoder.local.RoomDao
import ir.androidcoder.local.dataClass.DynamicTheme
import ir.androidcoder.mapper.toThemeEntity
import ir.androidcoder.mapper.toThemeModel
import javax.inject.Inject

class ThemeSource @Inject constructor(private val myDao: RoomDao) {

    suspend fun insertDynamicThemeStateRep(state: DynamicTheme) =
        myDao.insertDynamicTheme(state)

    suspend fun getDynamicThemeState(): DynamicTheme =
        myDao.getDynamicThemeState()


}