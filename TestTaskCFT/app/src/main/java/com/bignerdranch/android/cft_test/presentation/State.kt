package com.bignerdranch.android.cft_test.presentation

sealed class State {

    object ConnectionError : State()
    class OtherError(val message: String) : State()
}