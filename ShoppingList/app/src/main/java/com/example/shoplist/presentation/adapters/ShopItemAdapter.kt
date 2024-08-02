package com.example.shoplist.presentation.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.shoplist.R
import com.example.shoplist.databinding.ItemShopDisabledBinding
import com.example.shoplist.databinding.ItemShopEnabledBinding
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.presentation.ShopItemDiffCallback
import com.example.shoplist.presentation.ShowItemViewHolder

class ShopItemAdapter() : ListAdapter<ShopItem, ShowItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), layout, parent, false
        )
        return ShowItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        when (binding) {
            is ItemShopDisabledBinding -> {
                with(binding) {
                    tvItemName.text = shopItem.name
                    tvItemName.paintFlags = tvItemName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    tvItemCount.text = shopItem.count.toString()
                }
            }
            is ItemShopEnabledBinding -> {
                with(binding) {
                    tvItemName.text = shopItem.name
                    tvItemCount.text = shopItem.count.toString()
                }
            }
        }
        with(binding){
            root.setOnLongClickListener {
                onShopItemLongClickListener?.invoke(shopItem)
                true
            }
            root.setOnClickListener {
                onShopItemClickListener?.invoke(shopItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (shopItem.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val MAX_POOL_SIZE = 30
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
    }
}