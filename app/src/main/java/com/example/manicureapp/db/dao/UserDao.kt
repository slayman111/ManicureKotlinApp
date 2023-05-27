package com.example.manicureapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.manicureapp.db.entities.User

@Dao
interface UserDao {

  @Query("SELECT * FROM user")
  fun getAll(): List<User>

  @Query("SELECT * FROM user WHERE uid = :uid")
  fun getById(uid: Int): User

  @Query("SELECT * FROM user WHERE phone = :phone AND password = :password")
  fun getByPhoneAndPassword(phone: String, password: String): User

  @Insert
  fun insertAll(vararg users: User)

  @Delete
  fun delete(user: User)
}