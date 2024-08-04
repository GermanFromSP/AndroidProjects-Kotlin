package com.bignerdranch.android.cft_test.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bignerdranch.android.cft_test.data.database.models.UserDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDbModel: UserDbModel)

    @Query("SELECT * FROM users")
    fun getUsersList(): Flow<List<UserDbModel>>

    @Query("SELECT * FROM users WHERE id == :id")
    fun getUser(id: Int): Flow<UserDbModel>

}