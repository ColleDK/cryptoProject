package com.example.cryptoproject.web

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetroFitClient {

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(WebService.BASE_URL.url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }


}