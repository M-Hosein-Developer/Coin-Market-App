package ir.androidcoder.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.repositories.mainRepo.MainRepository
import ir.androidcoder.repositories.themeRepo.ThemeRepository
import ir.androidcoder.repositories.MainRepositoryImpl
import ir.androidcoder.repositories.ThemeRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryImplModule {

    @Binds
    abstract fun mainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun themeRepository(themeRepositoryImpl: ThemeRepositoryImpl): ThemeRepository

}