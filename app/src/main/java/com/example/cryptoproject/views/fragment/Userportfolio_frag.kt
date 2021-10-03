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
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoproject.R
import com.example.cryptoproject.adapters.StartListAdapter
import com.example.cryptoproject.db.DBHelper
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.viewModels.StartViewModel

class Userportfolio_frag : Fragment(), View.OnClickListener  {
    private val viewModel: StartViewModel by viewModels()
    private lateinit var adapter: StartListAdapter
    private val listOfCryptos = mutableListOf<Crypto>()
    private var dbHelper: DBHelper? = null
    private var prefs: SharedPreferences? = null
    private lateinit var transactionButton: Button
    private lateinit var infoText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_userportfolio, container, false)

        // get the recyclerview
        val cyclerView = root.findViewById<RecyclerView>(R.id.recyclerView_portfolio)

        // create the adapter
        adapter = StartListAdapter(listOfCryptos, requireContext())

        // set the adapter on the recyclerview
        cyclerView.adapter = adapter

        // set the layoutmanager
        cyclerView.layoutManager = LinearLayoutManager(requireContext())

        transactionButton = root.findViewById(R.id.transaction_button_portfolio)
        transactionButton.setOnClickListener(this)

        infoText = root.findViewById(R.id.infotext_portfolio)

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

        // TODO get the users currently owned assets

        // If there is no data in our viewmodel we get the information of the cryptos
        viewModel.getCryptos()

        // set up the observer for the viewmodel
        viewModel.cryptoList.observe(this, Observer {
            println(it.toString())
            listOfCryptos.clear()
            listOfCryptos.addAll(it)

            Handler(Looper.getMainLooper()).postDelayed({
                adapter.notifyDataSetChanged()
            }, 0)
        })
    }

    override fun onDestroy() {
        // we only want to close the db connection when the activity is destroyed
        dbHelper?.close()
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        if (v == transactionButton){
            // TODO change to transaction activity
        }
    }
}