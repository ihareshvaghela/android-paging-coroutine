package com.example.app.data.paging

import androidx.paging.PagingSource
import com.example.app.data.api.ApiHelper
import com.example.app.data.model.passenger.Passenger

class PassengersDataSource(private val apiHelper: ApiHelper) : PagingSource<Int, Passenger>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return try {

            val nextPageNumber = params.key ?: 0
            val response = apiHelper.getPassengers(nextPageNumber)

            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}