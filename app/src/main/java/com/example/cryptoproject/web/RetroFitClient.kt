package com.example.cryptoproject.web

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetroFitClient {

    val retrofit: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(WebService.BASE_URL.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }


}