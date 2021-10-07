package com.example.cryptoproject.db.dao

import androidx.room.*
import com.example.cryptoproject.db.entitiy.CryptoEntity

@Dao
interface CryptoDao {
    @Query("SELECT * FROM CryptoEntity")
    suspend fun getCryptos(): List<CryptoEntity>

    @Query("SELECT * FROM CryptoEntity WHERE name LIKE :name")
    suspend fun getCrypto(name: String): CryptoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrypto(vararg cryptos: CryptoEntity)

    @Delete
    suspend fun deleteCrypto(crypto: CryptoEntity)
}