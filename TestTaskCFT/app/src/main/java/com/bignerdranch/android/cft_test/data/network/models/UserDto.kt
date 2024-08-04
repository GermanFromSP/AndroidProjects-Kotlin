package com.bignerdranch.android.cft_test.data.network.models

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("name")
    val userName: UserName? = null,
    @SerializedName("location")
    val userLocation: UserLocation? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("login")
    val login: Login? = null,
    @SerializedName("dob")
    val birthdayDate: BirthdayDate? = null,
    @SerializedName("registered")
    val registrationDate: RegistrationDate? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("cell")
    val cell: String? = null,
    @SerializedName("picture")
    val profilePictures: Picture? = null,
    )

data class UserName(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("first")
    val firstName: String? = null,
    @SerializedName("last")
    val lastName: String? = null
)

data class UserLocation(
    @SerializedName("street")
    @Embedded
    val street: Street? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("postcode")
    val postcode: String? = null,
    @SerializedName("coordinates")
    @Embedded
    val coordinates: Coordinates? = null,
    @SerializedName("timezone")
    @Embedded
    val timezone: Timezone? = null,
)

data class Street(
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("name")
    val name: String? = null
)

data class Coordinates(
    @SerializedName("latitude")
    val latitude: String? = null,
    @SerializedName("longitude")
    val longitude: String? = null,
)

data class Timezone(
    @SerializedName("offset")
    val offset: String? = null,
)

data class Login(
    @SerializedName("username")
    val username: String? = null,
)

data class RegistrationDate(
    @SerializedName("date")
    val registerDate: String? = null,
    @SerializedName("age")
    val yearsAgo: Int? = null,
)

data class BirthdayDate(
    @SerializedName("date")
    val birthDate: String? = null,
    @SerializedName("age")
    val age: Int? = null,
)

data class Picture(
    @SerializedName("large")
    val largeSize: String? = null,

)