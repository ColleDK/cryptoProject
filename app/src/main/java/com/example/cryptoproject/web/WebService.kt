package com.example.cryptoproject.web

import com.example.cryptoproject.models.CryptoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    object BASE_URL{
        val url = "https://api.coincap.io/v2/"
    }

    @GET("assets/{name}")
    suspend fun getCrypto(@Path("name") shortName: String): Call<CryptoData>
}