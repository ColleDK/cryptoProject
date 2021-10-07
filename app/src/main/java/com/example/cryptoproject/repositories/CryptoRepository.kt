package com.example.cryptoproject.repositories

import com.example.cryptoproject.db.DBRoom
import com.example.cryptoproject.db.entitiy.CryptoEntity.Companion.toEntity
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.web.RetroFitClient

class CryptoRepository(
    private val apiClient: RetroFitClient,
    private val dbRoom: DBRoom) {


    suspend fun getCrypto(id: String): Crypto {
        return dbRoom.cryptoDao().getCrypto(id).toModel()
    }

    suspend fun loadCrypto(id: String){
        // load the crypto from the api and make it a model
        val model = apiClient.retrofit.getCrypto(id).data.toModel()
        val dbModel = model.toEntity()
        dbRoom.cryptoDao().insertCrypto(dbModel)
    }

    suspend fun getCryptos(): MutableList<Crypto> {
        val list = apiClient.retrofit.getCryptos().data
        val result = mutableListOf<Crypto>()
        for (dto in list){
            val model = dto.toModel()
            val dbModel = model.toEntity()
            dbRoom.cryptoDao().insertCrypto(dbModel)
            result.add(model)
        }
        return result
    }


}