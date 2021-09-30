package com.example.cryptoproject.web

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(WebService.BASE_URL.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }


}