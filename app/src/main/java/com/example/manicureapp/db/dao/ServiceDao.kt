package com.example.manicureapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.manicureapp.db.entities.Service

@Dao
interface ServiceDao {

  @Query("SELECT * FROM service")
  fun getAll(): List<Service>

  @Query("SELECT * FROM service WHERE uid = :uid")
  fun getById(uid: Int): Service

  @Insert
  fun insertAll(vararg services: Service)

  @Delete
  fun delete(service: Service)
}