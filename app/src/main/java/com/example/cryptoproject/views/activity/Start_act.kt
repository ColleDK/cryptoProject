package com.example.cryptoproject.views.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import com.example.cryptoproject.R
import com.example.cryptoproject.db.DBHelper
import com.example.cryptoproject.viewModels.StartViewModel
import com.example.cryptoproject.views.fragment.Cryptolist_start_frag
import com.example.cryptoproject.views.fragment.Userportfolio_frag

class Start_act : AppCompatActivity(), View.OnClickListener {
    private val viewModel: StartViewModel by viewModels()
    private var dbHelper: DBHelper? = null
    private var prefs: SharedPreferences? = null
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        supportFragmentManager.beginTransaction()
            .replace(R.id.cryptoListLayout_start, Cryptolist_start_frag())
            .commit()

        textView = findViewById<TextView>(R.id.pointsStart)
        textView.text = "Points 10000 USD"
        textView.setOnClickListener(this)

    }


    override fun onStart() {
        super.onStart()
        // Create the database helper
        dbHelper = DBHelper(this)

        // create preferencemanager
        prefs = PreferenceManager.getDefaultSharedPreferences(this)

        // TODO set observer for user and check if user has been created before

    }

    override fun onDestroy() {
        // we only want to close the db connection when the activity is destroyed
        dbHelper?.close()
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        if (v == textView){
            supportFragmentManager.beginTransaction()
                .replace(R.id.cryptoListLayout_start, Userportfolio_frag())
                .commit()
        }
    }
}