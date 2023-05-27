package com.example.manicureapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.manicureapp.db.entities.Master

@Dao
interface MasterDao {

  @Query("SELECT * FROM master")
  fun getAll(): List<Master>

  @Query("SELECT * FROM master WHERE uid = :uid")
  fun getById(uid: Int): Master

  @Insert
  fun insertAll(vararg masters: Master)

  @Delete
  fun delete(user: Master)
}