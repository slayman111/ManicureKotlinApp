package com.example.manicureapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Service(
  @ColumnInfo(name = "name") val name: String,
  @ColumnInfo(name = "description") val description: String,
  @ColumnInfo(name = "image") val image: String,
  @ColumnInfo(name = "price") val price: Double,
) {
  @PrimaryKey(autoGenerate = true)
  var uid: Int = 0
}
