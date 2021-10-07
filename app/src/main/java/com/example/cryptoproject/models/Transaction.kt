package com.example.cryptoproject.models

import java.sql.Timestamp

data class Transaction(val crypto: Crypto, val volume: Double, val price: Double, val timestamp: Timestamp) {
}