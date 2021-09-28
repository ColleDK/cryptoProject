package com.example.cryptoproject.web

import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {


    @GET("https://api.coincap.io/v2/assets/{name}")
    suspend fun getCrypto(@Path("name") shortName: String)
}