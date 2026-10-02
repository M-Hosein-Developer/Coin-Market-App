package ir.android.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.android.home.data.HomeRepository
import ir.android.home.domain.IHomeRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun mainRepository(mainRepository: HomeRepository): IHomeRepository

}