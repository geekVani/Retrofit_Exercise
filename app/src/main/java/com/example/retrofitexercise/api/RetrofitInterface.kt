package com.example.retrofitexercise.api

import com.example.retrofitexercise.model.retrofit.Item
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("entries")
    fun getAllData(): Call<Item>
}