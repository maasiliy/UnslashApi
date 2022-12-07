package com.example.unsplashapi.ui.listOfImage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapi.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelListOfImage @Inject constructor(private val repositoryListOfImage: RepositoryListOfImage): ViewModel(){

    var liveDataImage: MutableLiveData<Data> = MutableLiveData()


    init {
        getData()
    }

    fun getData() = viewModelScope.launch {
        val response = repositoryListOfImage.getData()
        if (response.isSuccessful) liveDataImage.postValue(response.body())
        else Log.d("Network error", response.errorBody().toString())
    }

}