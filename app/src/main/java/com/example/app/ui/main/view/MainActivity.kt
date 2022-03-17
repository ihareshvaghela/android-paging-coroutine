package com.example.app.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.data.api.ApiHelper
import com.example.app.data.api.RetrofitBuilder
import com.example.app.data.model.User
import com.example.app.databinding.ActivityMainBinding
import com.example.app.ui.base.ViewModelFactory
import com.example.app.ui.main.adapter.MainAdapter
import com.example.app.ui.main.viewmodel.MainViewModel
import com.example.app.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpViewModel()
        setUpUI()
        setUpObserver()
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(
                MainViewModel::class.java
            )
    }

    private fun setUpUI() {
        binding.mRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.mRecyclerView.context,
                (binding.mRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.mRecyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.mRecyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { user -> retrieveList(user) }
                    }
                    Status.ERROR -> {
                        binding.mRecyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.mRecyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
        }
    }
}