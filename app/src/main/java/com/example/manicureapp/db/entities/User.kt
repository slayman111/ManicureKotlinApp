package com.example.manicureapp.db.entities

import androidx.room.*

@Entity
data class User(
  @ColumnInfo(name = "full_name") val fullName: String,
  @ColumnInfo(name = "email") val email: String,
  @ColumnInfo(name = "phone") val phone: String,
  @ColumnInfo(name = "password") val password: String,
) {
  @PrimaryKey(autoGenerate = true)
  var uid: Int = 0
}