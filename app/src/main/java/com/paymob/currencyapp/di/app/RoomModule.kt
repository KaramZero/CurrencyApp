package com.paymob.currencyapp.di.app

import android.content.Context
import androidx.room.Room
import com.paymob.currencyapp.model.localSource.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {


    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RoomDb::class.java,
        "historyRates"
    ).build()


    @Singleton
    @Provides
    fun provideHistoryRatesDao(db: RoomDb) = db.historyRatesDao()

}