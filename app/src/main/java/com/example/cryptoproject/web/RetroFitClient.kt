package com.example.cryptoproject.web

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroFitClient {

    // Will create a retrofit object for the base url of webservice
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(WebService.BASE_URL.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }

    val retrofitPic by lazy {
        // cause the gson converter needs lenient for some reason
        val gson = GsonBuilder()
                .setLenient()
                .create()

        Retrofit.Builder()
                .baseUrl(WebService.BASE_URL_PICS.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(WebService::class.java)
    }


}