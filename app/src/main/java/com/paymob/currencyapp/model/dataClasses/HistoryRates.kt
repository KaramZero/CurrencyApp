package com.paymob.currencyapp.model.dataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "historyRates")
data class HistoryRates(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id"            ) var id        : Int?     = null,
    @SerializedName("date"          ) var date      : Long?     = null,
    @SerializedName("fromCurrency"  ) var fromCurrency  : String?  = null,
    @SerializedName("toCurrency"    ) var toCurrency    : String?  = null,
    @SerializedName("rate"          ) var rate      : Double?   = 0.0
)