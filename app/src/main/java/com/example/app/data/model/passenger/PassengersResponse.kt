package com.example.app.data.model.passenger

data class PassengersResponse(
    val data: List<Passenger>,
    val totalPages: Int,
    val totalPassengers: Int
)