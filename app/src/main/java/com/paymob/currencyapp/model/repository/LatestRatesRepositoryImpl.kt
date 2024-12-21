package com.paymob.currencyapp.model.repository

import com.paymob.currencyapp.domain.LatestRatesRepository
import com.paymob.currencyapp.model.dataClasses.LatestRatesResponse
import com.paymob.currencyapp.model.dataClasses.ViewState
import com.paymob.currencyapp.model.remoteSource.LatestRatesRemoteSource
import com.paymob.currencyapp.utilities.networkHelper.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LatestRatesRepositoryImpl @Inject constructor(private val remoteSource: LatestRatesRemoteSource) :
    LatestRatesRepository {
    override suspend fun getLatestRates(
    ): ViewState<LatestRatesResponse> = safeApiCall {
        remoteSource.getLatestRates()
    }
}