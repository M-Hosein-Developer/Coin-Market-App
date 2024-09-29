package com.example.coinmarket.di.module

import android.content.Context
import androidx.room.Room
import ir.androidcoder.local.MyDatabase
import ir.androidcoder.local.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : ir.androidcoder.local.MyDatabase {
        return Room.databaseBuilder(context , ir.androidcoder.local.MyDatabase::class.java , "databse.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: ir.androidcoder.local.MyDatabase) : ir.androidcoder.local.RoomDao {
        return database.roomDao()
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) : Context{
        return context
    }

}