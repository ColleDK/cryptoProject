package com.example.cryptoproject.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoproject.R
import com.example.cryptoproject.adapters.StartListAdapter
import com.example.cryptoproject.models.CryptoData

class Start : AppCompatActivity() {
    private lateinit var listAdapter: StartListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // get the recyclerview
        val cyclerView = findViewById<RecyclerView>(R.id.recyclerView_start)

        // TODO change to observer of viewmodel
        val adapterList = mutableListOf<CryptoData>()
        for (i in 0..5) {
            adapterList.add(CryptoData("Bitcoin","BTC", null, 3.13, i+0.0, 3219.231))
            println(adapterList[i].toString())
        }

        // create the adapter
        listAdapter = StartListAdapter(adapterList, this)

        // set the adapter on the recyclerview
        cyclerView.adapter = listAdapter
        //listAdapter.notifyItemInserted(adapterList.size-1)

        // set the layoutmanager
        cyclerView.layoutManager = LinearLayoutManager(this)

    }


    override fun onStart() {
        super.onStart()
        // TODO if no cryptos get from web
    }
}