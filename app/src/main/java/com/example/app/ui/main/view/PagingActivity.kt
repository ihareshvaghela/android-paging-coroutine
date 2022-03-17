package com.example.app.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.data.api.ApiHelper
import com.example.app.data.api.RetrofitBuilder
import com.example.app.databinding.ActivityPagingBinding
import com.example.app.ui.base.ViewModelFactory
import com.example.app.ui.main.adapter.PassengersAdapter
import com.example.app.ui.main.adapter.PassengersLoadStateAdapter
import com.example.app.ui.main.viewmodel.PassengersViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {

    lateinit var binding: ActivityPagingBinding
    lateinit var viewModel: PassengersViewModel
    lateinit var passengerAdapter: PassengersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupViewModel()
        setupView()
        setupList()
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(
                PassengersViewModel::class.java
            )
    }

    private fun setupView() {
        passengerAdapter = PassengersAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            //adapter = passengerAdapter
            adapter = passengerAdapter.withLoadStateHeaderAndFooter(
                header = PassengersLoadStateAdapter { passengerAdapter.retry() },
                footer = PassengersLoadStateAdapter { passengerAdapter.retry() }
            )
            setHasFixedSize(true)
        }
    }

    private fun setupList() {
        lifecycleScope.launch {
            viewModel.passenger.collectLatest {
                passengerAdapter.submitData(it)
            }
        }
    }
}