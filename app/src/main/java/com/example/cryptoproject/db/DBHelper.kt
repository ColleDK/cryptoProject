package com.example.cryptoproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.cryptoproject.models.CryptoDto
import com.example.cryptoproject.models.UserDto

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


        object UserEntry : BaseColumns{
            const val TABLE_NAME="User"
            const val COL_BALANCE = "balance"
            const val COL_TRANSACTIONS = "transactions"
        }

        private const val SQL_CREATE_ENTRIES_CRYPTO =
            "CREATE TABLE ${CryptoEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${CryptoEntry.COL_NAME} TEXT," +
                    "${CryptoEntry.COL_SYMBOL} TEXT," +
                    "${CryptoEntry.COL_PRICE} DOUBLE," +
                    "${CryptoEntry.COL_CHANGEPERCENT} DOUBLE," +
                    "${CryptoEntry.COL_SUPPLY} DOUBLE)"

        private const val SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE ${UserEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${UserEntry.COL_BALANCE} DOUBLE," +
                    "${UserEntry.COL_TRANSACTIONS} Integer," +
                    "FOREIGN KEY(${UserEntry.COL_TRANSACTIONS}) REFERENCES Transactions(_id))"


        private const val SQL_DELETE_ENTRIES_CRYPTO = "DROP TABLE IF EXISTS ${CryptoEntry.TABLE_NAME}"
        private const val SQL_DELETE_ENTRIES_USER = "DROP TABLE IF EXISTS ${UserEntry.TABLE_NAME}"
    }



    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES_CRYPTO)
        db?.execSQL(SQL_CREATE_ENTRIES_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES_CRYPTO)
        db?.execSQL(SQL_DELETE_ENTRIES_USER)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


    val allCryptoDtos:MutableList<CryptoDto>
        get() {
            val listCryptos = mutableListOf<CryptoDto>()
            val selectQuery = "SELECT * FROM ${CryptoEntry.TABLE_NAME}"
            val cursor = writableDB.rawQuery(selectQuery, null)
            with(cursor){
                while (moveToNext()){
                    val name = getString(getColumnIndexOrThrow(CryptoEntry.COL_NAME))
                    val symbol = getString(getColumnIndexOrThrow(CryptoEntry.COL_SYMBOL))
                    val price = getDouble(getColumnIndexOrThrow(CryptoEntry.COL_PRICE))
                    val changePercent = getDouble(getColumnIndexOrThrow(CryptoEntry.COL_CHANGEPERCENT))
                    val supply = getDouble(getColumnIndexOrThrow(CryptoEntry.COL_SUPPLY))
                    listCryptos.add(CryptoDto(name, symbol, price, changePercent, supply))
                }
            }
            cursor.close()
            return listCryptos
        }

    fun addCrypto(cryptoDto: CryptoDto){
        val values = ContentValues().apply {
            put(CryptoEntry.COL_NAME, cryptoDto.name)
            put(CryptoEntry.COL_SYMBOL, cryptoDto.symbol)
            put(CryptoEntry.COL_PRICE, cryptoDto.priceUsd)
            put(CryptoEntry.COL_CHANGEPERCENT, cryptoDto.changePercent24Hr)
            put(CryptoEntry.COL_SUPPLY, cryptoDto.supply)
        }

        val newRowId = writableDB?.insert(CryptoEntry.TABLE_NAME, null, values)
    }

    fun updateCrypto(cryptoDto: CryptoDto): Int{
        val values = ContentValues().apply {
            put(CryptoEntry.COL_NAME, cryptoDto.name)
            put(CryptoEntry.COL_SYMBOL, cryptoDto.symbol)
            put(CryptoEntry.COL_PRICE, cryptoDto.priceUsd)
            put(CryptoEntry.COL_CHANGEPERCENT, cryptoDto.changePercent24Hr)
            put(CryptoEntry.COL_SUPPLY, cryptoDto.supply)
        }

        return writableDB.update(CryptoEntry.TABLE_NAME, values, "${CryptoEntry.COL_NAME}=?", arrayOf(cryptoDto.name))
    }

    fun deleteCrypto(cryptoDto: CryptoDto){
        writableDB.delete(CryptoEntry.TABLE_NAME, "${CryptoEntry.COL_NAME}=?", arrayOf(cryptoDto.name))
    }


    fun getUser(): UserDto{
        val selectQuery = "SELECT * FROM ${UserEntry.TABLE_NAME}"
        val cursor = writableDB.rawQuery(selectQuery, null)
        var balance: Double = 10000.0
        var transactionIDs: List<Int>

        with(cursor){
            while (moveToNext()){
                balance = getDouble(getColumnIndexOrThrow(UserEntry.COL_BALANCE))
                transactionIDs = listOf(getInt(getColumnIndexOrThrow(UserEntry.COL_TRANSACTIONS)))
            }
        }
        cursor.close()
        return UserDto(balance, listOf());
    }

    fun addUser(userDto: UserDto){
        val values = ContentValues().apply {
            put(UserEntry.COL_BALANCE, userDto.balance)
            put(UserEntry.COL_TRANSACTIONS, 0)
        }

        val newRowId = writableDB?.insert(UserEntry.TABLE_NAME, null, values)
    }


    fun updateUser(userDto: UserDto): Int{
        val values = ContentValues().apply {
            put(UserEntry.COL_BALANCE, userDto.balance)
            put(UserEntry.COL_TRANSACTIONS, 0)
        }

        return writableDB.update(UserEntry.TABLE_NAME, values,null, null)
    }

    fun deleteUser(){
        writableDB.delete(UserEntry.TABLE_NAME, null, null)
    }


}