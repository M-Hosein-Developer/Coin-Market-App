package ir.androidcoder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.local.RoomDao
import ir.androidcoder.remote.ApiService
import ir.androidcoder.remote.ApiServicePrice
import ir.androidcoder.source.CryptoSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    @Singleton
    fun provideCryptoSource(apiService: ApiService ,dollarApi : ApiServicePrice, dao: RoomDao) : CryptoSource = CryptoSource(apiService , dollarApi , dao)

}