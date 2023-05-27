package com.example.manicureapp.db.dao

import androidx.room.*
import com.example.manicureapp.db.entities.Order

@Dao
interface OrderDao {

  @Query("SELECT * FROM `Order`")
  fun getAll(): List<Order>

  @Query("SELECT * FROM `Order` WHERE user_uid = :userUid")
  fun getByUserId(userUid: Int): Order

  @Insert
  fun insertAll(vararg orders: Order)

  @Delete
  fun delete(order: Order)
}