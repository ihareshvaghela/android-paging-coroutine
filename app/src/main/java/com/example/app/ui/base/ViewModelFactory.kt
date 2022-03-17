package com.example.app.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.data.api.ApiHelper
import com.example.app.data.repository.MainRepository
import com.example.app.ui.main.viewmodel.MainViewModel
import com.example.app.ui.main.viewmodel.PassengersViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        } else if (modelClass.isAssignableFrom(PassengersViewModel::class.java)) {
            return PassengersViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}