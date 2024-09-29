package com.example.coinmarket.di.module

import ir.androidcoder.remote.ApiService
import ir.androidcoder.remote.ApiServicePrice
import com.example.coinmarket.util.BASE_URL
import com.example.coinmarket.util.BASE_URL_PRICE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    @Named("CoinMarket")
    fun provideCoinMarketRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("Price")
    fun providePriceRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_PRICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("CoinMarket") retrofit: Retrofit): ir.androidcoder.remote.ApiService {
        return retrofit.create(ir.androidcoder.remote.ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServicePrice(@Named("Price") retrofit: Retrofit): ir.androidcoder.remote.ApiServicePrice {
        return retrofit.create(ir.androidcoder.remote.ApiServicePrice::class.java)
    }

}