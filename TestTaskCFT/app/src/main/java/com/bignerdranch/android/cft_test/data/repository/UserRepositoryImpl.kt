package com.bignerdranch.android.cft_test.data.repository

import android.app.Application
import com.bignerdranch.android.cft_test.data.database.UserInfoDao
import com.bignerdranch.android.cft_test.data.database.UsersDataBase
import com.bignerdranch.android.cft_test.data.mapper.UserMapper
import com.bignerdranch.android.cft_test.data.network.ApiFactory
import com.bignerdranch.android.cft_test.data.network.ApiService
import com.bignerdranch.android.cft_test.domain.User
import com.bignerdranch.android.cft_test.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val mapper: UserMapper,
    private val userDao: UserInfoDao,
    private val apiService: ApiService

) : UserRepository {

    override suspend fun updateUserList() {

        val usersListDto = apiService.loadUsersList()
        val newList = mapper.mapDtoToDbModel(usersListDto)

        newList?.forEach {
            userDao.insertUser(it)
        }
    }

    override fun getUserList(): Flow<List<User>> = userDao.getUsersList()
        .map {
            it.map { userDbModel ->
                mapper.mapDbModelToEntity(userDbModel)
            }
        }

    override fun getUser(id: Int): Flow<User> = userDao.getUser(id)
        .map { userDbModel ->
            mapper.mapDbModelToEntity(userDbModel)
        }
}