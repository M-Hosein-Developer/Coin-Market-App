package com.example.coinmarket.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.usecases.themeUsecase.ThemeUsecase
import ir.androidcoder.usecases.themeUsecase.ThemeUsecaseImpl
import ir.androidcoder.usecases.mainUsecase.MainUsecaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UsecaseModuleImpl {

//    @Binds
//    abstract fun mainUsecase(mainUsecaseImpl: MainUsecaseImpl): MainUsecase

    @Binds
    abstract fun themeUsecase(themeUsecaseImpl: ThemeUsecaseImpl): ThemeUsecase

}