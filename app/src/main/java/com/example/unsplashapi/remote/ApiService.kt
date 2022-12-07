package com.example.unsplashapi.remote

import com.example.unsplashapi.model.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("photos")
    suspend fun getData(@Query("client_id") client_id: String): Response<Data>

}