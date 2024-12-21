package com.paymob.currencyapp.model.dataClasses

import com.google.gson.annotations.SerializedName


open class BaseApiResponse {
    @SerializedName("success"   ) var success   : Boolean? = null
    @SerializedName("error"   ) var error   : Error?   = Error()

}

data class Error (

    @SerializedName("code" ) var code : Int?    = null,
    @SerializedName("info" ) var info : String? = null

)