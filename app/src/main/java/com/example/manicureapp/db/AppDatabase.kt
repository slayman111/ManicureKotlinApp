package com.example.manicureapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.manicureapp.db.dao.MasterDao
import com.example.manicureapp.db.dao.OrderDao
import com.example.manicureapp.db.dao.ServiceDao
import com.example.manicureapp.db.dao.UserDao
import com.example.manicureapp.db.entities.Master
import com.example.manicureapp.db.entities.Order
import com.example.manicureapp.db.entities.Service
import com.example.manicureapp.db.entities.User

@Database(entities = [Order::class, User::class, Master::class, Service::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun orderDao(): OrderDao
  abstract fun userDao(): UserDao
  abstract fun masterDao(): MasterDao
  abstract fun serviceDao(): ServiceDao
}