package com.example.shoplist.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.databinding.ActivityMainBinding
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.presentation.adapters.ShopItemAdapter
import javax.inject.Inject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as ShopListApp).component

    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var shopItemAdapter: ShopItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupOnSwiped()
        setupFABClickListener()

        viewModel.shopList.observe(this) {
            shopItemAdapter.submitList(it)
        }

        thread {
            val cursor = contentResolver.query(
                Uri.parse("content://com.example.shoplist/shop_items"),
                null,
                null,
                null,
                null,
                null,
            )

            while (cursor?.moveToNext() == true) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val count = cursor.getInt(cursor.getColumnIndexOrThrow("count"))
                val enabled = cursor.getInt(cursor.getColumnIndexOrThrow("enabled")) > 0
                val shopItem = ShopItem(name, count, enabled, id)
                Log.d("MainActivity", shopItem.toString())
            }
            cursor?.close()
        }


    }

    override fun onEditingFinished() {
        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    private fun isOnePaneMode(): Boolean {
        return binding.shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        shopItemAdapter = ShopItemAdapter()
        with(binding) {
            with(rvShopItem) {
                adapter = shopItemAdapter

                recycledViewPool.setMaxRecycledViews(
                    ShopItemAdapter.VIEW_TYPE_ENABLED,
                    ShopItemAdapter.MAX_POOL_SIZE
                )
                recycledViewPool.setMaxRecycledViews(
                    ShopItemAdapter.VIEW_TYPE_DISABLED,
                    ShopItemAdapter.MAX_POOL_SIZE
                )
            }
        }
        setupLongClickListener()
        setupClickListener()
    }

    private fun setupClickListener() {
        shopItemAdapter.onShopItemClickListener = {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))

            }
        }
    }

    private fun setupLongClickListener() {
        shopItemAdapter.onShopItemLongClickListener = {
            viewModel.editShowItem(it)
        }
    }

    private fun setupFABClickListener() {
        binding.btnAddItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }

        }
    }

    private fun setupOnSwiped() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                viewModel.removeShopItem(shopItemAdapter.currentList[viewHolder.adapterPosition])
                thread {
                    contentResolver.delete(
                        Uri.parse("content://com.example.shoplist/shop_items"),
                        null,
                        arrayOf(shopItemAdapter.currentList[viewHolder.adapterPosition].id.toString())
                    )
                }

            }
        }).attachToRecyclerView(binding.rvShopItem)
    }


}


