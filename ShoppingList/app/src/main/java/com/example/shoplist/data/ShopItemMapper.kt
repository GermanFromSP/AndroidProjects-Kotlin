package com.example.shoplist.data

import com.example.shoplist.domain.ShopItem
import javax.inject.Inject

class ShopItemMapper @Inject constructor() {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        count = shopItem.count,
        enabled = shopItem.enabled,
        name = shopItem.name
    )
    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        id = shopItemDbModel.id,
        count = shopItemDbModel.count,
        enabled = shopItemDbModel.enabled,
        name = shopItemDbModel.name
    )
    fun mapDbListToEntityList(shopItem: List<ShopItemDbModel>) = shopItem.map {
        mapDbModelToEntity(it)
    }
}