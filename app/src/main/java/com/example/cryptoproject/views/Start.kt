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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // get the recyclerview
        val cyclerView = findViewById<RecyclerView>(R.id.recyclerView_start)

        // TODO change to observer of viewmodel
        val adapterList = mutableListOf<Crypto>()

        // create the adapter
        listAdapter = StartListAdapter(adapterList, this)

        // set the adapter on the recyclerview
        cyclerView.adapter = listAdapter
        //listAdapter.notifyItemInserted(adapterList.size-1)

        // set the layoutmanager
        cyclerView.layoutManager = LinearLayoutManager(this)


        viewModel.getCrypto("ethereum")

        // create an observer for the cryptos with mvvm pattern
        viewModel.cryptos.observe(this, Observer {
            // remove all old elements and add the new ones
            println("OBSERVED NEW VALUE")
            Log.d("OBSERVER",it.toString())
            adapterList.clear()
            adapterList.addAll(listOf(it))
            listAdapter.notifyDataSetChanged()
        })
    }


    override fun onStart() {
        super.onStart()
        // TODO if no cryptos get from web
    }
}