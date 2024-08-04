package com.bignerdranch.android.cft_test.di.components

import android.app.Application
import com.bignerdranch.android.cft_test.di.ApplicationScope
import com.bignerdranch.android.cft_test.di.modules.DataModule
import com.bignerdranch.android.cft_test.di.modules.DomainModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, DomainModule::class])
interface ApplicationComponent {

    fun fragmentComponentFactory(): FragmentComponent.Factory

    @Component.Factory
    interface ApplicationFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}