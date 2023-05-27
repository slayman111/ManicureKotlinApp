package com.example.manicureapp.db

import android.content.Context
import androidx.room.Room

class DbSingleton private constructor() {

  companion object {

    @Volatile
    private var db: AppDatabase? = null

    fun getInstance(context: Context) =
      db ?: synchronized(this) {
        db ?: Room.databaseBuilder(
          context,
          AppDatabase::class.java,
          "manicure_db"
        ).allowMainThreadQueries().build()
      }
  }
}