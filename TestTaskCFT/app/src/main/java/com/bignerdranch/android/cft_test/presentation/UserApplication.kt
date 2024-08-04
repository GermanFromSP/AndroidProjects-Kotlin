package com.bignerdranch.android.cft_test.presentation

import android.app.Application
import com.bignerdranch.android.cft_test.di.components.DaggerApplicationComponent

class UserApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}