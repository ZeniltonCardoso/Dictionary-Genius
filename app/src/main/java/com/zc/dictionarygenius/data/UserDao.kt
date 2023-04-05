package com.zc.dictionarygenius.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addList(users: List<UserLocal>)

    @Query("SELECT * FROM Users")
    fun getAll(): Flow<List<UserLocal>>

    @Delete
    fun deleteUsers(vararg users: UserLocal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: UserLocal)
}