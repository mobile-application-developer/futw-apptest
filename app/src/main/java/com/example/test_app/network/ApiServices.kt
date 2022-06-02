package com.example.test_app.network

import com.example.test_app.data.ProjectData
import com.example.test_app.utils.Util.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServices {

    @GET("projects")
    fun getprojectlist(): Call<List<ProjectData>>

    companion object {

        var apiServices: ApiServices? = null

        fun getInstance(): ApiServices {
            if (apiServices == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiServices = retrofit.create(ApiServices::class.java)
            }
            return apiServices!!
        }
    }
}
