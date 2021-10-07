package com.example.cryptoproject.db.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.web.dto.CryptoDto

@Entity
data class CryptoEntity(
    @PrimaryKey val name: String,
    val symbol: String,
    var priceUsd: Double,
    var changePercent24Hr: Double,
    var supply: Double) {

    fun toModel(): Crypto = Crypto(name, symbol, priceUsd, changePercent24Hr, supply, null)

    companion object {
        fun Crypto.toEntity(): CryptoEntity =
            CryptoEntity(name, symbol, priceUsd, changePercent24Hr, supply)
    }
}