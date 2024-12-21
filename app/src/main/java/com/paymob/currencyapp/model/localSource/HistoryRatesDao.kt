package com.paymob.currencyapp.model.localSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paymob.currencyapp.model.dataClasses.HistoryRates

@Dao
interface HistoryRatesDao {


    @Query("""
        SELECT * FROM historyRates 
        WHERE date >= :daysAgo
        ORDER BY date DESC
    """)
    fun getHistory(daysAgo: Long): List<HistoryRates>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRate(rate: HistoryRates)
}