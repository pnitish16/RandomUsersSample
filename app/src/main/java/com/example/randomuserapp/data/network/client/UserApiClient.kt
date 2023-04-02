package com.example.randomuserapp.data.network.client

import com.example.randomuserapp.data.network.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiClient {

  @GET("api")
  suspend fun getRandomUsers(@Query("results") results: Int= 10, @Query("page") page: Int): Response<ApiResponse>

}