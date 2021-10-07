package com.example.cryptoproject.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptoproject.R
import com.example.cryptoproject.db.DBRoom

class Transaction_act : AppCompatActivity() {
    private lateinit var db: DBRoom


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
    }


    override fun onStart() {
        super.onStart()
        db = DBRoom.build(this)



    }


    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }
}