package com.example.coinmarket.di.module

import com.example.coinmarket.model.repository.mainRepo.MainRepository
import com.example.coinmarket.model.repository.mainRepo.MainRepositoryImpl
import com.example.coinmarket.model.repository.themeRepo.ThemeRepository
import com.example.coinmarket.model.repository.themeRepo.ThemeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun mainRepository(repository : MainRepositoryImpl) : MainRepository

    @Binds
    abstract fun themeRepository(repository : ThemeRepositoryImpl) : ThemeRepository


}