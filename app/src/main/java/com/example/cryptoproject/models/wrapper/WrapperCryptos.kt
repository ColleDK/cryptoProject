package com.example.cryptoproject.models.wrapper

import com.example.cryptoproject.models.CryptoDto

// A data wrapper for when loading all cryptos
data class WrapperCryptos(val data: MutableList<CryptoDto>)