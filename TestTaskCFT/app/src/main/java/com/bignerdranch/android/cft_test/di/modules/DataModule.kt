package com.bignerdranch.android.cft_test.di.modules

import android.app.Application
import com.bignerdranch.android.cft_test.data.database.UserInfoDao
import com.bignerdranch.android.cft_test.data.database.UsersDataBase
import com.bignerdranch.android.cft_test.data.network.ApiFactory
import com.bignerdranch.android.cft_test.data.network.ApiService
import com.bignerdranch.android.cft_test.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    companion object{

        @Provides
        @ApplicationScope
        fun provideCatsInfoDao(application: Application): UserInfoDao {
            return UsersDataBase.getInstance(application).usersDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService = ApiFactory.apiService
    }
}