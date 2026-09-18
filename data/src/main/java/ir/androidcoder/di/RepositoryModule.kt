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
import ir.androidcoder.source.ThemeSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun mainRepository(source: CryptoSource) : MainRepositoryImpl = MainRepositoryImpl(source)

    @Provides
    @Singleton
    fun themeRepository(source: ThemeSource) : ThemeRepositoryImpl = ThemeRepositoryImpl(source)

}