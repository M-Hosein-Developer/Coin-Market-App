package com.example.coinmarket.model.repository.themeRepo

import com.example.coinmarket.model.dataClass.DynamicTheme
import com.example.coinmarket.model.database.RoomDao
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(private val myDao: RoomDao) : ThemeRepository {


    override suspend fun insertDynamicThemeStateRep(state: DynamicTheme) = myDao.insertDynamicTheme(state)

    override suspend fun getDynamicThemeState(): DynamicTheme = myDao.getDynamicThemeState()


}