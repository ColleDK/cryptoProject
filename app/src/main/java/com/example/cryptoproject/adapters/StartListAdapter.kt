package com.example.cryptoproject.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoproject.R
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.views.activity.DetailedCrypto_act
import com.example.cryptoproject.views.activity.Start_act

class StartListAdapter(private val cryptos: MutableList<Crypto>, private var ctx: Context): RecyclerView.Adapter<StartListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartListAdapter.ViewHolder {
        this.ctx = parent.context
        val inflater = LayoutInflater.from(ctx)

        // inflate the custom layout
        val view = inflater.inflate(R.layout.start_liste_element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StartListAdapter.ViewHolder, position: Int) {
        val itemCrypto = cryptos[position]
        holder.bindCrypto(itemCrypto, position, ctx)
    }

    override fun getItemCount() = cryptos.size


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var crypto: Crypto? = null
        private var position: Int? = null

        init {
            v.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val ctx = v?.context
            val intent = Intent(ctx, DetailedCrypto_act::class.java)
            intent.putExtra("currentCrypto", crypto)

            ctx?.startActivity(intent)
            (ctx as Activity).finish()
        }

        fun bindCrypto(cryp: Crypto, pos: Int, ctx: Context){
            // set the crypto and the info into the element
            this.crypto = cryp
            this.position = pos
            view.findViewById<TextView>(R.id.cryptoName_start).text = cryp.name
            view.findViewById<TextView>(R.id.cryptoShortName_start).text = cryp.symbol
            view.findViewById<TextView>(R.id.cryptoPrice_start).text = String.format("%.3f", cryp.priceUsd)
            view.findViewById<TextView>(R.id.cryptoPercent_start).text = String.format("%.3f",cryp.changePercent24Hr)

            val color = if (cryp.changePercent24Hr > 0) ctx.resources.getColor(R.color.neonGreen) else ctx.resources.getColor(R.color.neonPink)
            view.findViewById<TextView>(R.id.cryptoPercent_start).setTextColor(color)


            view.findViewById<ImageView>(R.id.cryptoPic_start).setImageBitmap(cryp.picture)
        }
    }

}