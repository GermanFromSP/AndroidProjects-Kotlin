package com.bignerdranch.android.cft_test.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.cft_test.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            launchAllUsersFragment()
        }
    }

    private fun launchAllUsersFragment() {
        val fragment = UserListFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack("")
            .commit()
    }
}