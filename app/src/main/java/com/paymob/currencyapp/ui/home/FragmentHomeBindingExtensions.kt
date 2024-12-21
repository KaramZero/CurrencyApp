package com.paymob.currencyapp.ui.home

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import com.paymob.currencyapp.R
import com.paymob.currencyapp.databinding.FragmentHomeBinding
import com.paymob.currencyapp.model.dataClasses.ErrorType
import com.paymob.currencyapp.model.dataClasses.HistoryRates
import com.paymob.currencyapp.model.dataClasses.Rates
import com.paymob.currencyapp.model.dataClasses.ServerErrorType

fun FragmentHomeBinding.handelLoading() {
    progressBar.visibility = View.VISIBLE
    mainLayout.visibility = View.GONE
}

fun FragmentHomeBinding.handleIdle() {
    progressBar.visibility = View.GONE
    mainLayout.visibility = View.VISIBLE
}

fun FragmentHomeBinding.handleSuccess(ratesMap: Map<String, Double>?, context: Context) {
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

fun FragmentHomeBinding.handleError(error: ErrorType?, context: Context, retryAction: () -> Unit) {
    progressBar.visibility = View.GONE
    mainLayout.visibility = View.GONE

    val defaultMessage = context.getString(R.string.your_request_can_t_be_completed_at_the_moment)
    when (error) {
        is ErrorType.RequestNotCompletedError -> handleError(error.message ?: defaultMessage, retryAction)
        is ErrorType.ServerError -> {
            if (error.error == ServerErrorType.UNAUTHORIZED) {
                // Redirect to login screen
            } else {
                handleError(defaultMessage, retryAction)
            }
        }

        is ErrorType.DataParsingError -> handleError(defaultMessage, retryAction)
        is ErrorType.NetworkError -> handleError(context.getString(R.string.check_your_internet_connection), retryAction)
        is ErrorType.RequestTimeoutError -> handleError(context.getString(R.string.request_time_out_message), retryAction)
        else -> handleError(defaultMessage, retryAction)
    }
}

private fun FragmentHomeBinding.handleError(message: String, retryAction: () -> Unit) {
    Snackbar.make(
        swipeRefreshLayout,
        message,
        Snackbar.LENGTH_LONG
    ).setAction("Retry") {
        retryAction()
    }.show()
}


fun FragmentHomeBinding.convertCurrency(insert: (rate:HistoryRates) -> Unit) {
    val fromCurrency = spinnerFrom.selectedItem.toString()
    val toCurrency = spinnerTo.selectedItem.toString()
    val amount = etAmount.text.toString().toDoubleOrNull() ?: 1.0
    tvConvertedValue.text = Rates.convert(fromCurrency, toCurrency, amount).toString()
    insert(HistoryRates(
        fromCurrency = fromCurrency,
        toCurrency = toCurrency,
        rate = amount/tvConvertedValue.text.toString().toDouble(),
        date = System.currentTimeMillis()
        )
    )
}

fun FragmentHomeBinding.swapCurrencies() {
    val fromCurrency = spinnerFrom.selectedItemPosition
    val toCurrency = spinnerTo.selectedItemPosition

    spinnerFrom.setSelection(toCurrency)
    spinnerTo.setSelection(fromCurrency)
}