package com.bignerdranch.android.cft_test.domain

import com.bignerdranch.android.cft_test.data.network.models.BirthdayDate
import com.bignerdranch.android.cft_test.data.network.models.Login
import com.bignerdranch.android.cft_test.data.network.models.Picture
import com.bignerdranch.android.cft_test.data.network.models.RegistrationDate
import com.bignerdranch.android.cft_test.data.network.models.UserLocation
import com.bignerdranch.android.cft_test.data.network.models.UserName

data class User(
    val id: Int,
    val gender: String? = null,
    val userName: UserName? = null,
    val userLocation: UserLocation? = null,
    val email: String? = null,
    val login: Login? = null,
    val birthdayDate: BirthdayDate? = null,
    val registrationDate: RegistrationDate? = null,
    val phone: String? = null,
    val cell: String? = null,
    val profilePictures: Picture? = null,
)