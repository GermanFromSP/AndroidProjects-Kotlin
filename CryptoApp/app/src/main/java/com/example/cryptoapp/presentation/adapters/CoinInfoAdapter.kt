package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinViewHolder>() {
    var coinInfoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_coin_info, parent, false
        )
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        with(holder) {
            val coin = coinInfoList[position]
            with(binding) {
                with(coin) {
                    val symbolsTemplate = context.resources.getString(R.string.symbols_templates)
                    val lastUpdateTemplate =
                        context.resources.getString(R.string.last_update_template)
                    tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                    tvPrice.text = price.toString()
                    tvLastUpdate.text =
                        String.format(lastUpdateTemplate, lastUpdate)
                    Picasso.get().load(imageUrl).into(ivLogo)
                    itemView.setOnClickListener(View.OnClickListener {
                        onCoinClickListener?.onCoinClick(this)
                    })
                }
            }
        }
    }

    override fun getItemCount() = coinInfoList.size
    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }

    inner class CoinViewHolder(itemView: View) : ViewHolder(itemView) {
        val binding = ItemCoinInfoBinding.bind(itemView)
    }
}