package com.paymob.currencyapp.model.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkParams @Inject constructor() {
    companion object {
        const val BASE_URL = "http://data.fixer.io/api/"
        const val ACCESS_KEY = "884cf4bd30f714f96d411e484c6a8c70"
    }

}