package com.paymob.currencyapp.domain

import javax.inject.Inject


class LatestRatesUseCase @Inject constructor(
    private val repository: LatestRatesRepository
) {
    suspend operator fun invoke() = repository.getLatestRates()
}