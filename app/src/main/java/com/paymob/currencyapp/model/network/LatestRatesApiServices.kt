package com.paymob.currencyapp.model.network

import com.paymob.currencyapp.model.dataClasses.LatestRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LatestRatesApiServices {


    @GET("latest")
    suspend fun getLatestRates(
        @Query("access_key") accessKey : String,
    ): Response<LatestRatesResponse>

}