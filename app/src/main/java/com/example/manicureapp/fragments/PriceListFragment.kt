package com.example.manicureapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.manicureapp.R
import com.example.manicureapp.adapters.ServicesAdapter
import com.example.manicureapp.db.AppDatabase
import com.example.manicureapp.db.DbSingleton
import kotlinx.android.synthetic.main.price_list_fragment.*

class PriceListFragment : Fragment() {

  private lateinit var db: AppDatabase

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    (activity as AppCompatActivity?)!!.supportActionBar!!.show()

    return inflater.inflate(R.layout.price_list_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    db = DbSingleton.getInstance(requireActivity().applicationContext)

    servicesRecyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
    servicesRecyclerView.adapter = ServicesAdapter(
      view,
      db.serviceDao().getAll()
    )

    super.onViewCreated(view, savedInstanceState)
  }
}