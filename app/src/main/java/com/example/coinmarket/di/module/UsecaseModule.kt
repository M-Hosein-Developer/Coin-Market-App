package com.example.coinmarket.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.repositories.mainRepo.MainRepository
import ir.androidcoder.repositories.themeRepo.ThemeRepository
import ir.androidcoder.usecases.themeUsecase.ThemeUsecaseImpl
import ir.androidcoder.usecases.mainUsecase.MainUsecaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsecaseModule {

    @Provides
    @Singleton
    fun mainUsecase(repository: MainRepository): MainUsecaseImpl = MainUsecaseImpl(repository)

    @Provides
    @Singleton
    fun themeUsecase(repository: ThemeRepository): ThemeUsecaseImpl = ThemeUsecaseImpl(repository)

}