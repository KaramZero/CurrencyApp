package com.paymob.currencyapp.di.app

import com.paymob.currencyapp.model.network.NetworkParams
import com.paymob.currencyapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @TestOkHttpClient
    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient
        .Builder().apply {
            readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(loggingInterceptor)
            }
        }.build()

    @Singleton
    @Provides
    fun provideAppRetrofit(@TestOkHttpClient okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkParams.BASE_URL)
            .client(okHttpClient)
            .build()
}