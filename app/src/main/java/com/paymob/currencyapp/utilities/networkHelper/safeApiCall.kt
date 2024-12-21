package com.paymob.currencyapp.utilities.networkHelper

import android.util.Log
import com.paymob.currencyapp.model.dataClasses.ViewState

inline fun <T : Any> safeApiCall(actionId: Int = -1, block: () -> T): ViewState<T> {
    return try {
        ViewState.Success(block(), actionId)
    } catch (e: Exception) {
        Log.e("SafeApiCall", "safeApiCall: $e ")
        handleException(e, actionId)
    }
}
