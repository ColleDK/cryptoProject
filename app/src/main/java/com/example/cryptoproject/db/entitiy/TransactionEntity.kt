package com.example.cryptoproject.db.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoproject.models.CryptoDto
import java.sql.Timestamp

@Entity
data class TransactionEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name="crypto") val cryptoDto: CryptoDto,
    @ColumnInfo(name="volume") val volume: Double,
    @ColumnInfo(name="price")val price: Double,
    @ColumnInfo(name="timestamp") val timestamp: Timestamp){
}