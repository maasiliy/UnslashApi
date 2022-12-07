package com.example.unsplashapi.ui.listOfImage

import com.example.unsplashapi.remote.ApiService
import com.example.unsplashapi.utils.Constants
import javax.inject.Inject

class RepositoryListOfImage @Inject constructor(private val apiService: ApiService) {

    suspend fun getData() = apiService.getData(Constants.CLIENT_ID)

}