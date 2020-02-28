package com.example.weatherapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.City
import com.example.weatherapp.model.ListItem
import com.example.weatherapp.network.RetrofitServices
import java.lang.Exception

/**
 * Created by rajeshkumar07 on 28-02-2020.
 */
class MainRepository {
    val TAG = MainRepository::class.java.simpleName
    private val retrofitService = RetrofitServices.create()
    val successLiveData = MutableLiveData<List<ListItem>>()
    val failureLiveData = MutableLiveData<Boolean>()

    suspend fun getWeatherInfo(zipCode : String) {
        try {
            val response = retrofitService.getWeatherList(zipCode)
            if (response.isSuccessful) {
                Log.e(TAG, "Success=${response.body()}")
                successLiveData.postValue(response.body()?.list)
            } else {
                Log.e(TAG, "Failure=${response.body()}")
                failureLiveData.postValue(true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            failureLiveData.postValue(true)
        }
    }
}