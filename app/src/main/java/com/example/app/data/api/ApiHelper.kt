package com.example.app.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()

    suspend fun getPassengers(page: Int) = apiService.getPassengersData(page)

}