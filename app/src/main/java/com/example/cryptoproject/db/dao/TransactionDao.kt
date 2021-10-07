package com.example.cryptoproject.db.dao

import androidx.room.*
import com.example.cryptoproject.db.entitiy.TransactionEntity

@Dao
interface TransactionDao {
    @Query("SELECT * FROM TransactionEntity")
    suspend fun getTransactions(): List<TransactionEntity>

    @Query("SELECT * FROM TransactionEntity ORDER BY timestamp DESC")
    suspend fun getTransactionsInOrder(): List<TransactionEntity>

    @Insert
    suspend fun insertTransaction(vararg transactions: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transactions: TransactionEntity)

}