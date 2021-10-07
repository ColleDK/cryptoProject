package com.example.cryptoproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoproject.db.dao.CryptoDao
import com.example.cryptoproject.db.dao.TransactionDao
import com.example.cryptoproject.db.entitiy.CryptoEntity
import com.example.cryptoproject.db.entitiy.TransactionEntity

@Database(entities = [TransactionEntity::class, CryptoEntity::class], version = 1, exportSchema = true)
abstract class DBRoom : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun cryptoDao(): CryptoDao


    companion object {
        fun build(context: Context): DBRoom{
            return Room.databaseBuilder(context, DBRoom::class.java, "cryptoDBRoom")
                .build()
        }

    }
}