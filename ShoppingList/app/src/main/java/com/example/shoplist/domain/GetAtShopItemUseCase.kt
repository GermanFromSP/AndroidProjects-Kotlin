package com.example.shoplist.domain

import javax.inject.Inject

class GetAtShopItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    suspend   fun getAtShopItem(id:Int): ShopItem{
        return shopListRepository.getAtShopItem(id)
    }
}