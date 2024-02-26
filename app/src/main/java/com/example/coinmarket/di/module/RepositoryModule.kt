package com.example.coinmarket.di.module

import com.example.coinmarket.model.repository.MainRepository
import com.example.coinmarket.model.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun mainRepository(repository : MainRepositoryImpl) : MainRepository

}