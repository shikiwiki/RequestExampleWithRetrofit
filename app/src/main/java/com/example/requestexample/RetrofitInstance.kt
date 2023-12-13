package com.example.requestexample

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val mainUrl = "https://jsonplaceholder.typicode.com/"

    val api: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(mainUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiInterface::class.java)
    }
}