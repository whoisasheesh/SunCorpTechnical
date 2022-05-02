package com.example.suncorptechnicalchallenge.repository

import android.util.Log
import com.example.suncorptechnicalchallenge.api.Api
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

    fun fetchAstronauts(apiListener: OnComplete?) {
        Api.instance?.apiService?.astronautList?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    var res = ""
                    res = if (response.isSuccessful) {
                        response.body()!!.string()
                    } else {
                        response.errorBody()!!.string()
                    }

                    val resObj = JSONObject(res)
                    val resArray = resObj.getJSONArray("results")
                    astronautLists.clear()

                    for (i in 0 until resArray.length()) {
                        val eachAstronautObj = resArray.getJSONObject(i)
                        val astronautId = eachAstronautObj.getString("id")
                        val astronautName = eachAstronautObj.getString("name")
                        val astronautNationality = eachAstronautObj.getString("nationality")
                        val astronautDob = eachAstronautObj.getString("date_of_birth")
                        val astronautBio = eachAstronautObj.getString("bio")
                        val astronautThumbNail =
                            eachAstronautObj.getString("profile_image_thumbnail")
                        val astronautProfileIcon = eachAstronautObj.getString("profile_image")

                        astronautLists.add(
                            AstronautModel(
                                astronautId,
                                astronautName,
                                astronautNationality,
                                astronautThumbNail,
                                astronautDob,
                                astronautBio,
                                astronautProfileIcon
                            )
                        )
                    }
                    apiListener?.onComplete(response.isSuccessful, astronautLists)
                } catch (e: JSONException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("tag!!!!!!!!!!", "error" + t.message);
            }

        })
    }

    interface OnComplete {
        fun onComplete(isSuccess: Boolean, astronautModelArrayList: ArrayList<AstronautModel>)
    }

    companion object {
        var instance: AstronautRepository? = null
            get() {
                if (field == null) {
                    field = AstronautRepository()
                }
                return field
            }
            private set
    }
}
