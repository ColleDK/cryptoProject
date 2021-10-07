package com.example.cryptoproject.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cryptoproject.db.entitiy.CryptoEntity

@Dao
interface CryptoDao {
    @Query("SELECT * FROM CryptoEntity")
    suspend fun getCryptos(): List<CryptoEntity>

    @Insert
    suspend fun insertCrypto(vararg cryptos: CryptoEntity)

    @Delete
    suspend fun deleteCrypto(crypto: CryptoEntity)
}