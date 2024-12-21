package com.paymob.currencyapp.di.api


import com.paymob.currencyapp.domain.repo.LatestRatesRepository
import com.paymob.currencyapp.model.repository.LatestRatesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class LatestRatesRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindLatestRatesRepository(repository: LatestRatesRepositoryImpl): LatestRatesRepository

}