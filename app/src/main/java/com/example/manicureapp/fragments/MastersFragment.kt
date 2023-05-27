package com.example.manicureapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.manicureapp.R
import com.example.manicureapp.adapters.MastersAdapter
import com.example.manicureapp.constants.AppConstants
import com.example.manicureapp.db.AppDatabase
import com.example.manicureapp.db.DbSingleton
import kotlinx.android.synthetic.main.masters_fragment.*

class MastersFragment : Fragment() {

  private lateinit var db: AppDatabase
  private var userUid: Int = -1

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    (activity as AppCompatActivity).supportActionBar!!.show()

    userUid = arguments?.getInt(AppConstants.USER_UID)!!
    return inflater.inflate(R.layout.masters_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    db = DbSingleton.getInstance(requireActivity().applicationContext)

    mastersRecyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
    mastersRecyclerView.adapter = MastersAdapter(
      requireActivity().applicationContext,
      view,
      userUid,
      db.masterDao().getAll()
    )

    super.onViewCreated(view, savedInstanceState)
  }
}