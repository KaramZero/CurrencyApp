package com.paymob.currencyapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.paymob.currencyapp.databinding.ActivityMainBinding
import com.paymob.currencyapp.model.dataClasses.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        viewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is ViewState.Loading -> binding.handelLoading()
                is ViewState.Success ->
                    binding.handleSuccess(viewState.data.rates?.getCurrencies(), this)

                is ViewState.Error -> binding.handleError(viewState.error, this)
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