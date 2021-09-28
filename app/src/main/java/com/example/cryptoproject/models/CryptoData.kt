package com.example.cryptoproject.models

import android.graphics.Bitmap

data class CryptoData(val name: String, val symbol: String, var priceUSD: Double, var changePercent24Hr: Double, var supply: Double) {
    var picture : Bitmap? = null

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


}