package com.paymob.currencyapp.domain.repo

import com.paymob.currencyapp.model.dataClasses.HistoryRates
import com.paymob.currencyapp.model.dataClasses.LatestRatesResponse
import com.paymob.currencyapp.model.dataClasses.ViewState


interface LatestRatesRepository {

    suspend fun getLatestRates(): ViewState<LatestRatesResponse>
    suspend fun getLatestRatesFromDb(): ViewState<List<HistoryRates>>
    suspend fun insertRate(rate: HistoryRates)
}