package com.paymob.currencyapp.model.repository

import android.util.Log
import com.paymob.currencyapp.domain.repo.LatestRatesRepository
import com.paymob.currencyapp.model.dataClasses.ErrorType
import com.paymob.currencyapp.model.dataClasses.HistoryRates
import com.paymob.currencyapp.model.dataClasses.LatestRatesResponse
import com.paymob.currencyapp.model.dataClasses.ViewState
import com.paymob.currencyapp.model.localSource.HistoryRatesDao
import com.paymob.currencyapp.model.remoteSource.LatestRatesRemoteSource
import com.paymob.currencyapp.utilities.networkHelper.safeApiCall
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LatestRatesRepositoryImpl @Inject constructor(
    private val remoteSource: LatestRatesRemoteSource,
    private val dao: HistoryRatesDao,
) :
    LatestRatesRepository {
    override suspend fun getLatestRates(
    ): ViewState<LatestRatesResponse> = safeApiCall {
        remoteSource.getLatestRates()
    }

    override suspend fun insertRate(rate: HistoryRates) {
        try {
            dao.insertRate(rate)
        } catch (e: Exception) {
            Log.e("LatestRatesRepositoryImpl", "insertRate: $e", )
        }
    }

    override suspend fun getLatestRatesFromDb(): ViewState<List<HistoryRates>> {
        return try {
            ViewState.Success(dao.getHistory(getDaysAgo(4)))
        } catch (e: Exception) {
            ViewState.Error(ErrorType.RequestNotCompletedError(e.message))
        }
    }

    private fun getDaysAgo(days: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        return calendar.timeInMillis
    }

}