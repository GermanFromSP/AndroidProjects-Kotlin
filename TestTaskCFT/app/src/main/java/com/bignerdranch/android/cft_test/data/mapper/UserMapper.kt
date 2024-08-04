package com.bignerdranch.android.cft_test.data.mapper

import com.bignerdranch.android.cft_test.data.database.models.UserDbModel
import com.bignerdranch.android.cft_test.data.network.models.UsersListDto
import com.bignerdranch.android.cft_test.domain.User
import javax.inject.Inject

class UserMapper @Inject constructor() {
//
    fun mapDtoToDbModel(dtoModelList: UsersListDto): List<UserDbModel>? {
        return dtoModelList.userList?.map {
            UserDbModel(
                id = dtoModelList.userList.indexOf(it),
                gender = it.gender,
                userName = it.userName,
                userLocation = it.userLocation,
                email = it.email,
                login = it.login,
                birthdayDate = it.birthdayDate,
                registrationDate = it.registrationDate,
                phone = it.phone,
                cell = it.cell,
                profilePictures = it.profilePictures,
            )
        }
    }

    fun mapDbModelToEntity(userDbModel: UserDbModel) = User(
        id = userDbModel.id,
        gender = userDbModel.gender,
        userName = userDbModel.userName,
        userLocation = userDbModel.userLocation,
        email = userDbModel.email,
        login = userDbModel.login,
        birthdayDate = userDbModel.birthdayDate,
        registrationDate = userDbModel.registrationDate,
        phone = userDbModel.phone,
        cell = userDbModel.cell,
        profilePictures = userDbModel.profilePictures,
    )

}