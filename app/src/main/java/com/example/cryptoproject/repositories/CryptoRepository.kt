package com.example.cryptoproject.repositories

import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.web.WebService
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val webService: WebService,
) {
    private val apiKey: String = "Authorization:Bearer 7886b97c-0e4e-4e2c-9870-4b78ddaab437"

    suspend fun getCrypto(id: String): Crypto {
        /*val cached: Crypto = cryptoCache.get(id)
        if (cached != null){
            return cached
        }

        val freshCrypto = webService.getCrypto(id)
        cryptoCache.put(id, freshCrypto)
        return freshCrypto*/
        return webService.getCrypto(id).data
    }


}