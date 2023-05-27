package com.example.manicureapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.manicureapp.R
import com.example.manicureapp.constants.AppConstants
import com.example.manicureapp.db.AppDatabase
import com.example.manicureapp.db.DbSingleton
import kotlinx.android.synthetic.main.service_info_fragment.*

class ServiceInfoFragment : Fragment() {

  private lateinit var db: AppDatabase

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    (activity as AppCompatActivity?)!!.supportActionBar!!.show()

    return inflater.inflate(R.layout.service_info_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    db = DbSingleton.getInstance(requireActivity().applicationContext)

    val service = db.serviceDao().getById(arguments?.getInt(AppConstants.SERVICE_UID)!!)

    textViewServiceName.text = service.name
    textViewServiceDescription.text = service.description
    textViewServicePrice.text = "${service.price} руб."

    imageViewService.setImageResource(
      requireActivity().applicationContext.resources.getIdentifier(
        "drawable/${service.image}",
        "drawable",
        requireActivity().applicationContext.packageName
      )
    )

    imageButtonBack.setOnClickListener {
      Navigation.findNavController(view).popBackStack()
    }
  }
}