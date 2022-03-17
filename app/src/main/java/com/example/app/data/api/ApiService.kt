package com.example.app.data.api

import com.example.app.data.model.User
import com.example.app.data.model.passenger.PassengersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("passenger")
    suspend fun getPassengersData(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): PassengersResponse

    // https://api.instantwebtools.net/v1/passenger?page=0&size=2

}