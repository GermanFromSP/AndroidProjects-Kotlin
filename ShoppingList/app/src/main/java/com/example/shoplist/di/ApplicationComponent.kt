package com.example.shoplist.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.data.ShopListProvider
import com.example.shoplist.presentation.MainActivity
import com.example.shoplist.presentation.ShopItemActivity
import com.example.shoplist.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: ShopItemFragment)

    fun inject(activity: MainActivity)

    fun inject(provider: ShopListProvider)

    @Component.Factory
    interface ApplicationFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}