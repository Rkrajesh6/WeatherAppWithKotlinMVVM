package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.ListItem
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repository.MainRepository
import kotlinx.coroutines.launch

/**
 * Created by rajeshkumar07 on 28-02-2020.
 */
class MainViewModel : ViewModel(){
    private val mMainRepository = MainRepository()
    val successfulLiveData = mMainRepository.successLiveData
    val failureLiveData = mMainRepository.failureLiveData
    private var mutableLiveData = MutableLiveData<List<ListItem>>().apply { value = emptyList() }

    val listData : LiveData<List<ListItem>> = mutableLiveData

    fun getCurrentWeatherData(code : String){
        viewModelScope.launch {
            mMainRepository.getWeatherInfo(code)
        }
    }
}