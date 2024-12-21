package com.paymob.currencyapp.domain.useCases

import com.paymob.currencyapp.domain.repo.LatestRatesRepository
import com.paymob.currencyapp.model.dataClasses.HistoryRates
import javax.inject.Inject


class InsertRateUseCase @Inject constructor(
    private val repository: LatestRatesRepository
) {
    suspend operator fun invoke(rate: HistoryRates) = repository.insertRate(rate)
}