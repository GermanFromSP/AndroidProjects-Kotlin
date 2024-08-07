package com.example.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun getShopList(): LiveData<List<ShopItem>>
    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun removeShopItem(shopItem: ShopItem)
    suspend fun getAtShopItem(id: Int): ShopItem
    suspend fun editShopItem(shopItem: ShopItem)
}