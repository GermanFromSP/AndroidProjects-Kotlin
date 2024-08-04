package com.bignerdranch.android.cft_test.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bignerdranch.android.cft_test.data.database.models.UserDbModel

@Database(entities = [UserDbModel::class], version = 2, exportSchema = false)
abstract class UsersDataBase : RoomDatabase() {

    abstract fun usersDao(): UserInfoDao

    companion object {
        private var db: UsersDataBase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): UsersDataBase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    UsersDataBase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }
}