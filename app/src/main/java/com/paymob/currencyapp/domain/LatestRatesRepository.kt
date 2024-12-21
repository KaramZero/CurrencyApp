package com.paymob.currencyapp.domain

import com.paymob.currencyapp.model.dataClasses.LatestRatesResponse
import com.paymob.currencyapp.model.dataClasses.ViewState


interface LatestRatesRepository {

    suspend fun getLatestRates(): ViewState<LatestRatesResponse>
}