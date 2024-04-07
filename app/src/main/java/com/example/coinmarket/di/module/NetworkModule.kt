package com.example.coinmarket.di.module

import com.example.coinmarket.model.remote.ApiService
import com.example.coinmarket.model.remote.ApiServicePrice
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
    fun provideApiService(@Named("CoinMarket") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServicePrice(@Named("Price") retrofit: Retrofit): ApiServicePrice {
        return retrofit.create(ApiServicePrice::class.java)
    }

}