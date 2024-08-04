package com.bignerdranch.android.cft_test.di.modules

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.cft_test.di.ViewModelKey
import com.bignerdranch.android.cft_test.presentation.UserInfoViewModel
import com.bignerdranch.android.cft_test.presentation.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    fun bindUserListViewModel(viewModel: UserListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserInfoViewModel::class)
    fun bindUserInfoViewModel(viewModel: UserInfoViewModel): ViewModel
}