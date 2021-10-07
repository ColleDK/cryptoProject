package com.example.cryptoproject.web.dto

import com.example.cryptoproject.models.Crypto

data class CryptoDto(val name: String,
                     val symbol: String,
                     var priceUsd: Double,
                     var changePercent24Hr: Double,
                     var supply: Double) {
    fun toModel(): Crypto = Crypto(name, symbol, priceUsd, changePercent24Hr, supply, null)

}