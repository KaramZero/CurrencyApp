package com.paymob.currencyapp.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paymob.currencyapp.domain.useCases.GetHistoryRatesUseCase
import com.paymob.currencyapp.model.dataClasses.HistoryRates
import com.paymob.currencyapp.model.dataClasses.ViewState
import com.paymob.currencyapp.utilities.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val useCase: GetHistoryRatesUseCase
) : ViewModel() {


    private val _viewState = MutableLiveData<ViewState<List<HistoryRates>>>(ViewState.Idle())
    val viewState: MutableLiveData<ViewState<List<HistoryRates>>> = _viewState

    init {
        getLatestRatesFromDb()
    }

    fun getLatestRatesFromDb() {
        launchIO {
            _viewState.postValue(ViewState.Loading())
            useCase.invoke()
                .let {
                    _viewState.postValue(it)
                }
        }
    }

    fun clearViewState() {
        if (_viewState.value !is ViewState.Idle)
            _viewState.postValue(ViewState.Idle())
    }

}