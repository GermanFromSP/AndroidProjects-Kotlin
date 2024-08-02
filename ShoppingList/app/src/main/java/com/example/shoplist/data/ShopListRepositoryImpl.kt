package com.example.shoplist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(
    application: Application,
    private val mapper: ShopItemMapper,
    private val shopItemDao: ShopListDao
) : ShopListRepository {

    override fun getShopList(): LiveData<List<ShopItem>> = shopItemDao.getShopList().map {
        mapper.mapDbListToEntityList(it)
    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopItemDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun removeShopItem(shopItem: ShopItem) {
        shopItemDao.deleteShopItem(mapper.mapEntityToDbModel(shopItem).id)
    }

    override suspend fun getAtShopItem(id: Int): ShopItem {
        val dbModel = shopItemDao.getShopItem(id)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopItemDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

}