package com.example.cryptoproject.models

import android.graphics.Bitmap
import java.io.Serializable

data class CryptoDto(val name: String,
                     val symbol: String,
                     var priceUsd: Double,
                     var changePercent24Hr: Double,
                     var supply: Double) : Serializable{
    // The picture of a certain crypto
    // Make it transient so it wont be serialized cause problems
    @Transient var picture : Bitmap? = null

    enum class timeInterval{
        day,
        week,
        month,
        year,
        ytd,
        allTime
    }

    fun getPrices(interval: timeInterval){
        TODO()
    }

    override fun toString(): String {
        return "Name: $name, Symbol: $symbol, Price: $priceUsd, ChangePercent: $changePercent24Hr, Supply: $supply"
    }

}