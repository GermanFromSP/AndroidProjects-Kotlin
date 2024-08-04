package com.bignerdranch.android.cft_test.di.modules

import com.bignerdranch.android.cft_test.data.repository.UserRepositoryImpl
import com.bignerdranch.android.cft_test.domain.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindRepository(impl: UserRepositoryImpl): UserRepository
}