package com.paymob.currencyapp.domain.useCases

import com.paymob.currencyapp.domain.repo.LatestRatesRepository
import javax.inject.Inject


class GetHistoryRatesUseCase @Inject constructor(
    private val repository: LatestRatesRepository
) {
    suspend operator fun invoke() = repository.getLatestRatesFromDb()
}