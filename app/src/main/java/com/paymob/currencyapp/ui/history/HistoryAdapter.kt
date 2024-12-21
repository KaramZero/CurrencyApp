package com.paymob.currencyapp.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paymob.currencyapp.R
import com.paymob.currencyapp.databinding.ItemHistoryBinding
import com.paymob.currencyapp.model.dataClasses.HistoryRates
import java.util.Date


class HistoryAdapter : ListAdapter<HistoryRates, HistoryAdapter.ViewHolder>(
    HistoryRatesDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(item = currentItem)
    }


    class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryRates) {
            binding.apply {
                tvFromCurrency.text = item.fromCurrency
                tvToCurrency.text = item.toCurrency
                tvRate.text = root.context.getString(R.string.rate, item.rate.toString())
                tvDate.text =
                    root.context.getString(R.string.date, item.date?.let { Date(it).toString() })
            }
        }
    }

    class HistoryRatesDiffCallback : DiffUtil.ItemCallback<HistoryRates>() {
        override fun areItemsTheSame(oldItem: HistoryRates, newItem: HistoryRates): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoryRates, newItem: HistoryRates): Boolean {
            return oldItem == newItem
        }
    }

}
