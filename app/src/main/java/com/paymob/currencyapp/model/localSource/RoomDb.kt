package com.paymob.currencyapp.model.localSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paymob.currencyapp.model.dataClasses.HistoryRates


@Database(entities = [HistoryRates::class], version = 1)
abstract class RoomDb : RoomDatabase() {

    abstract fun historyRatesDao(): HistoryRatesDao
}