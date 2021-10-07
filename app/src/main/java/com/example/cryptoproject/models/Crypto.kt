package com.example.cryptoproject.models

import android.graphics.Bitmap
import java.io.Serializable

// Make the picture transient so it wont be serialized cause problems
data class Crypto(val name: String,
                  val symbol: String,
                  var priceUsd: Double,
                  var changePercent24Hr: Double,
                  var supply: Double,
                  @Transient var picture : Bitmap? = null) : Serializable{


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