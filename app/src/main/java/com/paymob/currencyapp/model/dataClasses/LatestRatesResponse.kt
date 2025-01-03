package com.paymob.currencyapp.model.dataClasses

import com.google.gson.annotations.SerializedName


data class LatestRatesResponse(
    @SerializedName("timestamp" ) var timestamp : Int?     = null,
    @SerializedName("base"      ) var base      : String?  = null,
    @SerializedName("date"      ) var date      : String?  = null,
    @SerializedName("rates"     ) var rates     : Rates?   = Rates()
) : BaseApiResponse()