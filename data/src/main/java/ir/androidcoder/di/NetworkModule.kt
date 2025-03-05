package ir.androidcoder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.BuildConfig
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
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("Price")
    fun providePriceRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
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