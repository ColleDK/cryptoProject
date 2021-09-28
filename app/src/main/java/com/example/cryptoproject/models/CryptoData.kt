package com.example.cryptoproject.models

import android.graphics.Bitmap

data class CryptoData(val name: String, val shortName: String, val picture: Bitmap?, var price: Double, var changePercent: Double, var volume: Double) {

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