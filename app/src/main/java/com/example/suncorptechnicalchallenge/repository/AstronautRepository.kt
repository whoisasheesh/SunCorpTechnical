package com.example.suncorptechnicalchallenge.repository

import com.example.suncorptechnicalchallenge.api.Api
import com.example.suncorptechnicalchallenge.interfaces.OnComplete
import com.example.suncorptechnicalchallenge.model.AstronautModel
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AstronautRepository {
    val astronautLists = ArrayList<AstronautModel>()
    fun fetchProperty(apiListener: OnComplete?) {
        Api.instance?.apiService?.astronautList?.enqueue(object:Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
