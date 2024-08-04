package com.bignerdranch.android.cft_test.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun updateUserList()
    fun getUserList(): Flow<List<User>>
    fun getUser(id: Int): Flow<User>

}