package com.example.cryptoproject.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoproject.R
import com.example.cryptoproject.adapters.StartListAdapter
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.viewModels.StartViewModel

class DetailedCrypto_act : AppCompatActivity() {
    private val viewModel: StartViewModel by viewModels()
    private lateinit var cyclerView: RecyclerView
    private lateinit var adapter: StartListAdapter
    private lateinit var currentCrypt: Crypto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_crypto)

        cyclerView = findViewById(R.id.recyclerView_detailedCrypto)

        // get the crypto from the intent
        currentCrypt = (intent.getSerializableExtra("currentCrypto") as? Crypto)!!

        // get the picture of the crypto
        viewModel.getCryptoPic(currentCrypt)

        viewModel.crypto.observe(this, Observer {
            println(it.toString())
            currentCrypt = it

            adapter = StartListAdapter(mutableListOf(currentCrypt), this)

            cyclerView.adapter = adapter

            Handler(Looper.getMainLooper()).postDelayed({
                adapter.notifyDataSetChanged()
            }, 0)
        })

        adapter = StartListAdapter(mutableListOf(currentCrypt), this)

        cyclerView.adapter = adapter


        cyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()


    }

}