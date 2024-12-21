package com.paymob.currencyapp.ui.history

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.paymob.currencyapp.R
import com.paymob.currencyapp.databinding.FragmentHistoryBinding
import com.paymob.currencyapp.model.dataClasses.ErrorType
import com.paymob.currencyapp.model.dataClasses.ServerErrorType

fun FragmentHistoryBinding.handelLoading() {
    progressBar.visibility = View.VISIBLE
    mainLayout.visibility = View.GONE
}

fun FragmentHistoryBinding.handleIdle() {
    progressBar.visibility = View.GONE
    mainLayout.visibility = View.VISIBLE
}

fun FragmentHistoryBinding.handleSuccess() {
    progressBar.visibility = View.GONE
    mainLayout.visibility = View.VISIBLE
}

fun FragmentHistoryBinding.handleError(error: ErrorType?, context: Context) {
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

private fun FragmentHistoryBinding.handleError(message: String) {
    Snackbar.make(
        swipeRefreshLayout,
        message,
        Snackbar.LENGTH_LONG
    ).show()
}