package com.example.cryptoproject.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoproject.R
import com.example.cryptoproject.adapters.StartListAdapter
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.viewModels.StartViewModel

class Start : AppCompatActivity() {
    private lateinit var listAdapter: StartListAdapter
    private val viewModel: StartViewModel by viewModels()
    private val listOfCryptos = mutableListOf<Crypto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // get the recyclerview
        val cyclerView = findViewById<RecyclerView>(R.id.recyclerView_start)

        // create the adapter
        listAdapter = StartListAdapter(listOfCryptos, this)

        // set the adapter on the recyclerview
        cyclerView.adapter = listAdapter
        //listAdapter.notifyItemInserted(adapterList.size-1)

        // set the layoutmanager
        cyclerView.layoutManager = LinearLayoutManager(this)

    }


    override fun onStart() {
        super.onStart()
        viewModel.getCryptos()
        viewModel.cryptoList.observe(this, Observer {
            println(it.toString())
            listOfCryptos.clear()
            listOfCryptos.addAll(it)
            listAdapter.notifyDataSetChanged()
        })
    }
}