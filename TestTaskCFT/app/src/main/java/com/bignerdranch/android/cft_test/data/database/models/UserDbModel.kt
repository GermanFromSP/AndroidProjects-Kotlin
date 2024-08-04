package com.bignerdranch.android.cft_test.data.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bignerdranch.android.cft_test.data.network.models.BirthdayDate
import com.bignerdranch.android.cft_test.data.network.models.Login
import com.bignerdranch.android.cft_test.data.network.models.Picture
import com.bignerdranch.android.cft_test.data.network.models.RegistrationDate
import com.bignerdranch.android.cft_test.data.network.models.UserLocation
import com.bignerdranch.android.cft_test.data.network.models.UserName

@Entity(tableName = "users")
data class UserDbModel(
    @PrimaryKey
    val id: Int,
    val gender: String? = null,
    @Embedded
    val userName: UserName? = null,
    @Embedded
    val userLocation: UserLocation? = null,
    val email: String? = null,
    @Embedded
    val login: Login? = null,
    @Embedded
    val birthdayDate: BirthdayDate? = null,
    @Embedded
    val registrationDate: RegistrationDate? = null,
    val phone: String? = null,
    val cell: String? = null,
    @Embedded
    val profilePictures: Picture? = null,
    val nationality: String? = null,
)
