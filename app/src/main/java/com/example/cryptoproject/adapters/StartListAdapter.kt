package com.example.cryptoproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoproject.R
import com.example.cryptoproject.models.CryptoData

class StartListAdapter(private val cryptos: MutableList<CryptoData>, private var ctx: Context): RecyclerView.Adapter<StartListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartListAdapter.ViewHolder {
        this.ctx = parent.context
        val inflater = LayoutInflater.from(ctx)

        // inflate the custom layout
        val view = inflater.inflate(R.layout.start_liste_element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StartListAdapter.ViewHolder, position: Int) {
        val itemCrypto = cryptos[position]
        holder.bindCrypto(itemCrypto, position)
    }

    override fun getItemCount() = cryptos.size


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var crypto: CryptoData? = null
        private var position: Int? = null

        init {
            v.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            println("Clicked item on position $position")
        }

        fun bindCrypto(cryp: CryptoData, pos: Int){
            // set the crypto and the info into the element
            this.crypto = cryp
            this.position = pos
            view.findViewById<TextView>(R.id.cryptoName_start).text = cryp.name
            view.findViewById<TextView>(R.id.cryptoShortName_start).text = cryp.symbol
            view.findViewById<TextView>(R.id.cryptoPrice_start).text = cryp.priceUSD.toString()
            view.findViewById<TextView>(R.id.cryptoPercent_start).text = cryp.changePercent24Hr.toString()
        }
    }

}