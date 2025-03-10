package ir.androidcoder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.local.RoomDao
import ir.androidcoder.remote.ApiService
import ir.androidcoder.remote.ApiServicePrice
import ir.androidcoder.repositories.MainRepositoryImpl
import ir.androidcoder.repositories.ThemeRepositoryImpl
import ir.androidcoder.source.CryptoSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun mainRepository(source: CryptoSource ,apiService: ApiService , apiServicePrice: ApiServicePrice , dao : RoomDao) : MainRepositoryImpl = MainRepositoryImpl(source ,apiService , apiServicePrice , dao)

    @Provides
    @Singleton
    fun themeRepository(dao: RoomDao) : ThemeRepositoryImpl = ThemeRepositoryImpl(dao)

}