package com.example.manicureapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
  @ColumnInfo(name = "master_uid") val masterUid: Int,
  @ColumnInfo(name = "service_uid") val serviceUid: Int,
  @ColumnInfo(name = "user_uid") val userUid: Int,
  @ColumnInfo(name = "date") val date: String,
  @ColumnInfo(name = "time") val time: String,
) {
  @PrimaryKey(autoGenerate = true)
  var uid: Int = 0
}