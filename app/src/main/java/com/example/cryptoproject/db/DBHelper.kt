package com.example.cryptoproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.cryptoproject.models.Crypto

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    private val writableDB: SQLiteDatabase = this.writableDatabase

    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "cryptoDB"

        object CryptoEntry : BaseColumns{
            const val TABLE_NAME="Crypto"
            const val COL_NAME = "name"
            const val COL_SYMBOL = "symbol"
            const val COL_PRICE = "priceUsd"
            const val COL_CHANGEPERCENT = "changePercent24Hr"
            const val COL_SUPPLY = "supply"
        }

        private const val SQL_CREATE_ENTRIES_CRYPTO =
            "CREATE TABLE ${CryptoEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${CryptoEntry.COL_NAME} TEXT," +
                    "${CryptoEntry.COL_SYMBOL} TEXT," +
                    "${CryptoEntry.COL_PRICE} DOUBLE," +
                    "${CryptoEntry.COL_CHANGEPERCENT} DOUBLE," +
                    "${CryptoEntry.COL_SUPPLY} DOUBLE)"



        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${CryptoEntry.TABLE_NAME}"
    }



    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES_CRYPTO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


    val allCryptos:MutableList<Crypto>
        get() {
            val listCryptos = mutableListOf<Crypto>()
            val selectQuery = "SELECT * FROM ${CryptoEntry.TABLE_NAME}"
            val cursor = writableDB.rawQuery(selectQuery, null)
            with(cursor){
                while (moveToNext()){
                    val name = getString(getColumnIndexOrThrow(CryptoEntry.COL_NAME))
                    val symbol = getString(getColumnIndexOrThrow(CryptoEntry.COL_SYMBOL))
                    val price = getDouble(getColumnIndexOrThrow(CryptoEntry.COL_PRICE))
                    val changePercent = getDouble(getColumnIndexOrThrow(CryptoEntry.COL_CHANGEPERCENT))
                    val supply = getDouble(getColumnIndexOrThrow(CryptoEntry.COL_SUPPLY))
                    listCryptos.add(Crypto(name, symbol, price, changePercent, supply))
                }
            }
            cursor.close()
            return listCryptos
        }

    fun addCrypto(crypto: Crypto){
        val values = ContentValues().apply {
            put(CryptoEntry.COL_NAME, crypto.name)
            put(CryptoEntry.COL_SYMBOL, crypto.symbol)
            put(CryptoEntry.COL_PRICE, crypto.priceUsd)
            put(CryptoEntry.COL_CHANGEPERCENT, crypto.changePercent24Hr)
            put(CryptoEntry.COL_SUPPLY, crypto.supply)
        }

        val newRowId = writableDB?.insert(CryptoEntry.TABLE_NAME, null, values)
    }

    fun updateCrypto(crypto: Crypto): Int{
        val values = ContentValues().apply {
            put(CryptoEntry.COL_NAME, crypto.name)
            put(CryptoEntry.COL_SYMBOL, crypto.symbol)
            put(CryptoEntry.COL_PRICE, crypto.priceUsd)
            put(CryptoEntry.COL_CHANGEPERCENT, crypto.changePercent24Hr)
            put(CryptoEntry.COL_SUPPLY, crypto.supply)
        }

        return writableDB.update(CryptoEntry.TABLE_NAME, values, "${CryptoEntry.COL_NAME}=?", arrayOf(crypto.name))
    }

    fun deleteCrypto(crypto: Crypto){
        writableDB.delete(CryptoEntry.TABLE_NAME, "${CryptoEntry.COL_NAME}=?", arrayOf(crypto.name))
    }


}