package com.paymob.currencyapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.paymob.currencyapp.databinding.FragmentHistoryBinding
import com.paymob.currencyapp.model.dataClasses.HistoryRates
import com.paymob.currencyapp.model.dataClasses.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoryViewModel by viewModels()
    private val historyAdapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewState()
    }

    private fun setupViews() {
        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getLatestRatesFromDb()
                swipeRefreshLayout.isRefreshing = false
            }
            recyclerViewHistory.apply {
                adapter = historyAdapter
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            }
        }
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is ViewState.Loading -> binding.handelLoading()
                is ViewState.Success -> {
                    val list:List<HistoryRates> = viewState.data
                    historyAdapter.submitList(list)
                    binding.handleSuccess()
                }

                is ViewState.Error -> binding.handleError(viewState.error, binding.root.context)
                is ViewState.Idle -> binding.handleIdle()
            }
        }
    }

}