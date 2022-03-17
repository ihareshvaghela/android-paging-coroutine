package com.example.app.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.app.data.api.ApiHelper
import com.example.app.data.paging.PassengersDataSource

class PassengersViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    val passenger = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        PassengersDataSource(apiHelper)
    }).flow/*.cachedIn(viewModelScope)*/
}