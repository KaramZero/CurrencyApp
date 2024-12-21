package com.paymob.currencyapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.paymob.currencyapp.databinding.FragmentHomeBinding
import com.paymob.currencyapp.model.dataClasses.ViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
                viewModel.getLatestRates()
                swipeRefreshLayout.isRefreshing = false
            }
            spinnerFrom.onItemSelectedListener = onItemSelected
            spinnerTo.onItemSelectedListener = onItemSelected
            btnConvert.setOnClickListener {
                convertCurrency()
            }
            btnSwap.setOnClickListener {
                swapCurrencies()
            }
        }
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is ViewState.Loading -> binding.handelLoading()
                is ViewState.Success ->
                    binding.handleSuccess(viewState.data.rates?.getCurrencies(), binding.root.context)

                is ViewState.Error -> binding.handleError(viewState.error,  binding.root.context)
                is ViewState.Idle -> binding.handleIdle()
            }
        }
    }

    private val onItemSelected = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            binding.convertCurrency()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            // Do nothing
        }
    }



}