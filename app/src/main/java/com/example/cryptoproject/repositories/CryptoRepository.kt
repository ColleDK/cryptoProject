package com.example.cryptoproject.repositories

import com.example.cryptoproject.web.WebService

class CryptoRepository {
    private val webService: WebService = TODO()
    private val apiKey: String = "Authorization:Bearer 7886b97c-0e4e-4e2c-9870-4b78ddaab437"

    suspend fun getCrypto(id: String) = webService.getCrypto("id")
    suspend fun getCryptos() = webService.getCryptos()


}