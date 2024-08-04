package com.bignerdranch.android.cft_test.data.network

import com.bignerdranch.android.cft_test.data.network.models.UsersListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun loadUsersList(
    @Query(QUERY_PARAM_TO_GET_USERS) amount: Int = USERS_AMOUNT
    ): UsersListDto

    companion object {
        private const val USERS_AMOUNT = 10
        private const val QUERY_PARAM_TO_GET_USERS = "results"
    }
}