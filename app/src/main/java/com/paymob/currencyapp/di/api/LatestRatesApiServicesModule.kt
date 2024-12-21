package com.paymob.currencyapp.di.api

import com.paymob.currencyapp.model.network.LatestRatesApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LatestRatesApiServicesModule {

    @Singleton
    @Provides
    fun provideLatestRatesApiServices(retrofit: Retrofit): LatestRatesApiServices =
        retrofit.create(LatestRatesApiServices::class.java)

}