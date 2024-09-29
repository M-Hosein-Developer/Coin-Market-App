package com.example.coinmarket.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.local.MyDatabase
import ir.androidcoder.local.RoomDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : MyDatabase {
        return Room.databaseBuilder(context , MyDatabase::class.java , "databse.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: MyDatabase) : RoomDao {
        return database.roomDao()
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) : Context{
        return context
    }

}