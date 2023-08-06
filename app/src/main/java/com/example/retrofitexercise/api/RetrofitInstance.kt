package com.example.retrofitexercise.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: RetrofitInterface by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.publicapis.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface::class.java )
    }
}