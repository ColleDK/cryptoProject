package com.example.cryptoproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoproject.R
import com.example.cryptoproject.models.Transaction

class TransactionListAdapter(private val transactions: MutableList<Transaction>, private var ctx: Context): RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionListAdapter.ViewHolder {
        this.ctx = parent.context
        val inflater = LayoutInflater.from(ctx)

        // inflate the custom layout
        val view = inflater.inflate(R.layout.transaction_liste_element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionListAdapter.ViewHolder, position: Int) {
        val itemTransaction = transactions[position]
        holder.bindTransaction(itemTransaction, position, ctx)
    }

    override fun getItemCount() = transactions.size


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var transaction: Transaction? = null
        private var position: Int? = null

        init {
            v.setOnClickListener(this)
        }


        override fun onClick(v: View?) {

        }

        fun bindTransaction(transaction: Transaction, pos: Int, ctx: Context){
            // set the crypto and the info into the element
            this.transaction = transaction
            this.position = pos

        }
    }

}