package com.example.coinmarket.model.repository.themeRepo

import ir.androidcoder.local.dataClass.DynamicTheme
import ir.androidcoder.local.RoomDao
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(private val myDao: ir.androidcoder.local.RoomDao) : ThemeRepository {


    override suspend fun insertDynamicThemeStateRep(state: ir.androidcoder.local.dataClass.DynamicTheme) = myDao.insertDynamicTheme(state)

    override suspend fun getDynamicThemeState(): ir.androidcoder.local.dataClass.DynamicTheme = myDao.getDynamicThemeState()


}