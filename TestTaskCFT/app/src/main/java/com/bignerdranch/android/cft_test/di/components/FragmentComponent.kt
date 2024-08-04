package com.bignerdranch.android.cft_test.di.components

import com.bignerdranch.android.cft_test.di.annotations.IdQualifier
import com.bignerdranch.android.cft_test.di.modules.ViewModelModule
import com.bignerdranch.android.cft_test.presentation.UserApplication
import com.bignerdranch.android.cft_test.presentation.UserInfoFragment
import com.bignerdranch.android.cft_test.presentation.UserListFragment
import dagger.BindsInstance
import dagger.Subcomponent

private const val DEFAULT_ID_VALUE = 0

@Subcomponent(modules = [ViewModelModule::class])
interface FragmentComponent {

    fun inject(fragment: UserListFragment)
    fun inject(fragment: UserInfoFragment)
    fun inject(application: UserApplication)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance @IdQualifier id: Int = DEFAULT_ID_VALUE
        ): FragmentComponent
    }
}