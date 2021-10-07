package com.example.cryptoproject.views.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoproject.R
import com.example.cryptoproject.adapters.StartListAdapter
import com.example.cryptoproject.db.DBHelper
import com.example.cryptoproject.models.CryptoDto
import com.example.cryptoproject.viewModels.StartViewModel

class Cryptolist_start_frag : Fragment(), View.OnClickListener {
    private val viewModel: StartViewModel by viewModels()
    private lateinit var adapter: StartListAdapter
    private val listOfCryptos = mutableListOf<CryptoDto>()
    private var dbHelper: DBHelper? = null
    private var prefs: SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_cryptolist_start, container, false)

        // get the recyclerview
        val cyclerView = root.findViewById<RecyclerView>(R.id.recyclerView_start)

        // create the adapter
        adapter = StartListAdapter(listOfCryptos, requireContext())

        // set the adapter on the recyclerview
        cyclerView.adapter = adapter

        // set the layoutmanager
        cyclerView.layoutManager = LinearLayoutManager(requireContext())

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        // Create the database helper
        dbHelper = DBHelper(requireContext())

        // create preferencemanager
        prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // If there is no data in our viewmodel we get the information of the cryptos
        viewModel.getCryptos()

        // set up the observer for the viewmodel
        viewModel.cryptoDtoList.observe(this, Observer {
            println(it.toString())
            listOfCryptos.clear()
            listOfCryptos.addAll(it)

            Handler(Looper.getMainLooper()).postDelayed({
                adapter.notifyDataSetChanged()
            }, 0)


            // if the database has never been initialized we insert all the new data
            if (!prefs?.getBoolean("DBInit", false)!!){
                for (crypto in listOfCryptos){
                    dbHelper?.addCrypto(crypto)
                }
                // set the prefs to true for future calls
                prefs?.edit()?.putBoolean("DBInit", true)?.apply()
            } else {
                // update cryptos
                for (crypto in listOfCryptos){
                    dbHelper?.updateCrypto(crypto)
                }
            }
        })
    }

    override fun onDestroy() {
        // we only want to close the db connection when the activity is destroyed
        dbHelper?.close()
        super.onDestroy()
    }

    override fun onClick(v: View?) {

    }
}