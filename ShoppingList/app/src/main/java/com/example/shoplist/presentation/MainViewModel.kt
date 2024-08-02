package com.example.shoplist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplist.domain.EditShopItemUseCase
import com.example.shoplist.domain.GetShopListUseCase
import com.example.shoplist.domain.RemoveShopItemUseCase
import com.example.shoplist.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val removeShopListUseCase: RemoveShopItemUseCase,
    private val editShopListUseCase: EditShopItemUseCase
) : ViewModel() {


    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            removeShopListUseCase.removeShopItem(shopItem)
        }
    }

    fun editShowItem(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editShopListUseCase.editShopItem(newShopItem)
        }
    }
}