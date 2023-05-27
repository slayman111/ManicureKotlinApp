package com.example.manicureapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Master(
  @ColumnInfo(name = "full_name") val fullName: String,
  @ColumnInfo(name = "specialities") val specialities: String,
  @ColumnInfo(name = "image") val image: String,
) {
  @PrimaryKey(autoGenerate = true)
  var uid: Int = 0
}