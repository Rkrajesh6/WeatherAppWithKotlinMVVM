package com.example.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.jetbrains.annotations.Contract

/**
 * Created by rajeshkumar07 on 28-02-2020.
 */
object Constants {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val REQUEST_TIMEOUT = 60L
    //const val API_KEY = "2cba5e7343bb1a4ae8002ab66aca23c9"
    //const val COUNTRY_KEY = "IN"
    /**
     * Created by : rajeshkumar07
     * Created Date/Time : 28-02-2020 10:35
     * Description : This function is using for checking network connected or not.
     */
    fun isNetworkAvailable(context: Context): Boolean {
        var isInternetConnected = false
        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkArray = connectivityManager.allNetworks
            for (network in networkArray) {
                val networkInfo = connectivityManager.getNetworkInfo(network)
                isInternetConnected = isInternetConnected || checkNetworkInfo(networkInfo)
            }
        } catch (ignore: Exception) {
        }
        return isInternetConnected
    }

    @Contract("null -> false")
    private fun checkNetworkInfo(networkInfo: NetworkInfo?): Boolean {
        if (networkInfo != null && networkInfo.isConnected) {
            when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> return true
                ConnectivityManager.TYPE_MOBILE -> return true
                ConnectivityManager.TYPE_ETHERNET -> return true
            }
        }
        return false
    }
}