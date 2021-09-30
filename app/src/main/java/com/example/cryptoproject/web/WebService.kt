package com.example.cryptoproject.web

import com.example.cryptoproject.models.Crypto
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    object BASE_URL{
        val url = "https://api.coincap.io/v2/"
    }

    @GET("assets/{id}")
    suspend fun getCrypto(@Path("id") shortName: String): Crypto
}