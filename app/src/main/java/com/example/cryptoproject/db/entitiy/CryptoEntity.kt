package com.example.cryptoproject.db.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CryptoEntity(
    @PrimaryKey val name: String,
    val symbol: String,
    var priceUsd: Double,
    var changePercent24Hr: Double,
    var supply: Double) {
}