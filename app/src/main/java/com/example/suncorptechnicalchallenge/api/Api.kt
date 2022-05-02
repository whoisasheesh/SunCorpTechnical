package com.example.suncorptechnicalchallenge.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class Api {
    val apiService: ApiService
        get() {
            val host = "http://spacelaunchnow.me/api/3.5.0/"
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder().baseUrl(host).client(client).build()
            return retrofit.create(ApiService::class.java)
        }

    companion object {
        var instance: Api? = null
            get() {
                if (field == null) field = Api()
                return field
            }
            private set
        const val apiAstronaut = "astronaut/"
        const val apiAstronautDetail = "1" //send astronaut id here
    }
}