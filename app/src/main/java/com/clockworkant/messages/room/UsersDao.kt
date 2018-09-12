package com.clockworkant.messages.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.clockworkant.messages.User

@Dao
interface UsersDao {

    @Insert
    fun insert(users: List<User>)

    @Delete
    fun delete(users: List<User>)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>
}

