package com.example.manicureapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.manicureapp.R

class HomeFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    (activity as AppCompatActivity).supportActionBar!!.show()

    return inflater.inflate(R.layout.home_fragment, container, false)
  }
}