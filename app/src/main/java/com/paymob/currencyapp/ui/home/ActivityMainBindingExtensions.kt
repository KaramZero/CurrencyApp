package com.paymob.currencyapp.ui.home

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import com.paymob.currencyapp.R
import com.paymob.currencyapp.databinding.ActivityMainBinding
import com.paymob.currencyapp.model.dataClasses.ErrorType
import com.paymob.currencyapp.model.dataClasses.Rates
import com.paymob.currencyapp.model.dataClasses.ServerErrorType

fun ActivityMainBinding.handelLoading() {
    progressBar.visibility = View.VISIBLE
    mainLayout.visibility = View.GONE
}

fun ActivityMainBinding.handleIdle() {
    progressBar.visibility = View.GONE
    mainLayout.visibility = View.VISIBLE
}

fun ActivityMainBinding.handleSuccess(ratesMap: Map<String, Double>?, context: Context) {
    progressBar.visibility = View.GONE
    mainLayout.visibility = View.VISIBLE

    // Retrieve rates and populate spinners
    if (ratesMap != null) {
        val currencyList = ratesMap.keys.toList() // Convert the Set to a List

        val spinnerAdapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            currencyList
        )

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinnerFrom.adapter = spinnerAdapter
        spinnerTo.adapter = spinnerAdapter
    }

}

fun ActivityMainBinding.handleError(error: ErrorType?, context: Context) {
    progressBar.visibility = View.GONE
    mainLayout.visibility = View.VISIBLE

    val defaultMessage = context.getString(R.string.your_request_can_t_be_completed_at_the_moment)
    when (error) {
        is ErrorType.RequestNotCompletedError -> handleError(error.message ?: defaultMessage)
        is ErrorType.ServerError -> {
            if (error.error == ServerErrorType.UNAUTHORIZED) {
                // Redirect to login screen
            } else {
                handleError(defaultMessage)
            }
        }

        is ErrorType.DataParsingError -> handleError(defaultMessage)
        is ErrorType.NetworkError -> handleError(context.getString(R.string.check_your_internet_connection))
        is ErrorType.RequestTimeoutError -> handleError(context.getString(R.string.request_time_out_message))
        else -> handleError(defaultMessage)
    }
}

private fun ActivityMainBinding.handleError(message: String) {
    Snackbar.make(
        swipeRefreshLayout,
        message,
        Snackbar.LENGTH_LONG
    ).show()
}


fun ActivityMainBinding.convertCurrency() {
    val fromCurrency = spinnerFrom.selectedItem.toString()
    val toCurrency = spinnerTo.selectedItem.toString()
    val amount = etAmount.text.toString().toDoubleOrNull() ?: 1.0
    tvConvertedValue.text = Rates.convert(fromCurrency, toCurrency, amount).toString()
}

fun ActivityMainBinding.swapCurrencies() {
    val fromCurrency = spinnerFrom.selectedItemPosition
    val toCurrency = spinnerTo.selectedItemPosition

    spinnerFrom.setSelection(toCurrency)
    spinnerTo.setSelection(fromCurrency)
}