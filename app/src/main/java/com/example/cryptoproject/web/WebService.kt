package com.example.cryptoproject.web

import com.example.cryptoproject.models.WrapperCrypto
import com.example.cryptoproject.models.WrapperCryptos
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    object BASE_URL{
        val url = "https://api.coincap.io/v2/"
    }

    @GET("assets/{id}")
    suspend fun getCrypto(@Path("id") id: String): WrapperCrypto

    @GET("assets/")
    suspend fun getCryptos(): WrapperCryptos
}