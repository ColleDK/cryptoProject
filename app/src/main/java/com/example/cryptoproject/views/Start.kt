package com.example.cryptoproject.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoproject.R
import com.example.cryptoproject.adapters.StartListAdapter
import com.example.cryptoproject.db.DBHelper
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.viewModels.StartViewModel

class Start : AppCompatActivity() {
    private val viewModel: StartViewModel by viewModels()
    private lateinit var adapter: StartListAdapter
    private val listOfCryptos = mutableListOf<Crypto>()
    private val dbHelper = DBHelper(this)
    private val prefs = PreferenceManager.getDefaultSharedPreferences(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // get the recyclerview
        val cyclerView = findViewById<RecyclerView>(R.id.recyclerView_start)

        // create the adapter
        adapter = StartListAdapter(listOfCryptos, this)

        // set the adapter on the recyclerview
        cyclerView.adapter = adapter

        // set the layoutmanager
        cyclerView.layoutManager = LinearLayoutManager(this)

    }


    override fun onStart() {
        super.onStart()
        // If there is no data in our viewmodel we get the information of the cryptos
        viewModel.getCryptos()

        // set up the observer for the viewmodel
        viewModel.cryptoList.observe(this, Observer {
            println(it.toString())
            listOfCryptos.clear()
            listOfCryptos.addAll(it)
            adapter.notifyDataSetChanged()
            // if the database has never been initialized we insert all the new data
            if (!prefs.getBoolean("DBInit", false)){
                for (crypto in listOfCryptos){
                    dbHelper.addCrypto(crypto)
                }
                // set the prefs to true for future calls
                prefs.edit().putBoolean("DBInit", true).apply()
            } else {
                // update cryptos
                for (crypto in listOfCryptos){
                    dbHelper.updateCrypto(crypto)
                }
            }
        })
    }

    override fun onDestroy() {
        // we only want to close the db connection when the activity is destroyed
        dbHelper.close()
        super.onDestroy()
    }
}