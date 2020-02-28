package com.example.weatherapp.network

import com.example.weatherapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by rajeshkumar07 on 28-02-2020.
 */
object RetrofitServices {

    private lateinit var mApiInterface: ApiServices

    /**
     * Created by : rajeshkumar07
     * Created Date/Time : 28-02-2020 10:34
     * Description : This mehtod is used to create the Retrofit instance and perform the retrofit related
     * opration.
     */
    fun create(): ApiServices {
        val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.readTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        mApiInterface = retrofit.create(ApiServices::class.java)
        return mApiInterface
    }
    /**
     * Created by : rajeshkumar07
     * Created Date/Time : 28-02-2020 10:34
     * Description : This method is used to see the request and response logger
     */
    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}