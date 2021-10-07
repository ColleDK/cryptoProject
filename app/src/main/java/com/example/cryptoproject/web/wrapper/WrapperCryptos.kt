package com.example.cryptoproject.web.wrapper

import com.example.cryptoproject.web.dto.CryptoDto

// A data wrapper for when loading all cryptos
data class WrapperCryptos(val data: MutableList<CryptoDto>)