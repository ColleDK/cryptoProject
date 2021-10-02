package com.example.cryptoproject.web

import com.example.cryptoproject.models.WrapperCrypto
import com.example.cryptoproject.models.WrapperCryptos
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WebService {
    // Helper object that contains the base url for the api
    object BASE_URL{
        val url = "https://api.coincap.io/v2/"
    }

    object BASE_URL_PICS{
        val url = "https://static.coincap.io/assets/icons/"
    }

    @Headers(
        "Accept: application/json",
        "Content-type: application/json",
        "Authorization:Bearer 7886b97c-0e4e-4e2c-9870-4b78ddaab437"
    )
    @GET("assets/{id}")
    suspend fun getCrypto(@Path("id") id: String): WrapperCrypto

    @Headers(
        "Accept: application/json",
        "Content-type: application/json",
        "Authorization:Bearer 7886b97c-0e4e-4e2c-9870-4b78ddaab437"
    )
    @GET("assets/")
    suspend fun getCryptos(): WrapperCryptos

    @Headers(
            "Content-Type: image/png; charset=binary",
            "Authorization:Bearer 7886b97c-0e4e-4e2c-9870-4b78ddaab437"
    )
    @GET("{id}@2x.png")
    fun getCryptoPics(@Path("id") id: String): Call<ResponseBody>

}