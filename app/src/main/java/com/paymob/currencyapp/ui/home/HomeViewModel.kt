package com.paymob.currencyapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paymob.currencyapp.domain.useCases.GetLatestRatesUseCase
import com.paymob.currencyapp.domain.useCases.InsertRateUseCase
import com.paymob.currencyapp.model.dataClasses.HistoryRates
import com.paymob.currencyapp.model.dataClasses.LatestRatesResponse
import com.paymob.currencyapp.model.dataClasses.ViewState
import com.paymob.currencyapp.utilities.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetLatestRatesUseCase,
    private val insertRateUseCase: InsertRateUseCase
) : ViewModel() {


    private val _viewState = MutableLiveData<ViewState<LatestRatesResponse>>(ViewState.Idle())
    val viewState: MutableLiveData<ViewState<LatestRatesResponse>> = _viewState

    init {
        getLatestRates()
    }

    fun getLatestRates() {
        launchIO {
            _viewState.postValue(ViewState.Loading())
            useCase.invoke()
                .let {
                    _viewState.postValue(it)
                }
        }
    }

    fun insertRate(rate: HistoryRates) {
        launchIO {
            insertRateUseCase.invoke(rate)
        }
    }

    fun clearViewState() {
        if (_viewState.value !is ViewState.Idle)
            _viewState.postValue(ViewState.Idle())
    }

}