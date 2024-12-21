package com.paymob.currencyapp.model.remoteSource

import com.paymob.currencyapp.model.dataClasses.LatestRatesResponse
import com.paymob.currencyapp.model.network.LatestRatesApiServices
import com.paymob.currencyapp.model.network.NetworkParams
import com.paymob.currencyapp.utilities.networkHelper.handleResponse
import javax.inject.Inject

class LatestRatesRemoteSource @Inject constructor(
    private val apiServices: LatestRatesApiServices
) {

    suspend fun getLatestRates(): LatestRatesResponse =
        handleResponse(
            apiServices.getLatestRates(
                accessKey = NetworkParams.ACCESS_KEY
            )
        )
}