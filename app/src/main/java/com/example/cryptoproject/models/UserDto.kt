package com.example.cryptoproject.models

import com.example.cryptoproject.db.entitiy.TransactionEntity

data class UserDto(var balance: Double, val transactions: List<TransactionEntity>) {

    fun getOwnedCryptos(){}

    fun getTransactions(){}

}