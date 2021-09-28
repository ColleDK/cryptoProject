package com.example.cryptoproject.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptoproject.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        // change to the start screen
        val intent = Intent(this, Start::class.java)
        startActivity(intent)
        finish()
    }
}