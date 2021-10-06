package com.example.cryptoproject.models

data class User(var balance: Double, val transactions: List<Transactions>) {

    fun getOwnedCryptos(){}

    fun getTransactions(){}

}