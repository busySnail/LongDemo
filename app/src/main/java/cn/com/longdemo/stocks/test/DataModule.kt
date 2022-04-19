package cn.com.longdemo.stocks.test

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideDataRepository(dataRepository: StocksRepositoryImpl): StocksRepository

}