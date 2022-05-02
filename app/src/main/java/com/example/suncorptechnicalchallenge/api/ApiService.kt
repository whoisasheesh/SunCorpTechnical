package com.example.suncorptechnicalchallenge.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @get:GET(Api.apiAstronaut)
    val astronautList: Call<ResponseBody>
}