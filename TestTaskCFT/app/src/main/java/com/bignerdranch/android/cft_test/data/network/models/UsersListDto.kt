package com.bignerdranch.android.cft_test.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersListDto(
    @SerializedName("results")
    @Expose
    val userList: List<UserDto>? = null
)