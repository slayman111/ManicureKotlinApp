package com.example.manicureapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_first)

    buttonStart.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java))
    }
  }
}