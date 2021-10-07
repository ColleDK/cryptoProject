package com.example.cryptoproject.models

import java.sql.Timestamp

data class TransactionDto(val cryptoDto: CryptoDto, val volume: Double, val price: Double, val timestamp: Timestamp) {
}