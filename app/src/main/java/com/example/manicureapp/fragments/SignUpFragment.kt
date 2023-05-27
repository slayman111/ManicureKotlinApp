package com.example.manicureapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.manicureapp.R
import com.example.manicureapp.constants.AppConstants
import com.example.manicureapp.db.AppDatabase
import com.example.manicureapp.db.DbSingleton
import com.example.manicureapp.db.entities.Master
import com.example.manicureapp.db.entities.Order
import com.example.manicureapp.db.entities.Service
import kotlinx.android.synthetic.main.sign_up_fragment.*


class SignUpFragment : Fragment() {

  private lateinit var db: AppDatabase
  private lateinit var services: List<Service>
  private lateinit var masters: List<Master>

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    (activity as AppCompatActivity?)!!.supportActionBar!!.show()

    return inflater.inflate(R.layout.sign_up_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    db = DbSingleton.getInstance(requireActivity().applicationContext)
    val userUid = arguments?.getInt(AppConstants.USER_UID)

    masters = db.masterDao().getAll()
    val mastersArrayAdapter: ArrayAdapter<String> =
      ArrayAdapter<String>(
        requireActivity().applicationContext,
        android.R.layout.simple_spinner_item,
        masters.map { m -> m.fullName }
      )
    mastersSpinner.adapter = mastersArrayAdapter

    services = db.serviceDao().getAll()
    val servicesArrayAdapter: ArrayAdapter<String> =
      ArrayAdapter<String>(
        requireActivity().applicationContext,
        android.R.layout.simple_spinner_item,
        services.map { s -> s.name }
      )
    servicesSpinner.adapter = servicesArrayAdapter

    imageButtonBack.setOnClickListener {
      Navigation.findNavController(view).popBackStack()
    }

    buttonSign.setOnClickListener {
      try {
        if (editTextDate.text.toString().isEmpty() || editTextTime.text.toString().isEmpty()) {
          throw Exception("Заполните все поля")
        }

        db.orderDao().insertAll(Order(
          masters[mastersSpinner.selectedItemPosition].uid,
          services[servicesSpinner.selectedItemPosition].uid,
          userUid!!,
          editTextDate.text.toString(),
          editTextTime.text.toString()
        ))

        Toast.makeText(
          requireActivity().applicationContext,
          "Ваша запись создана",
          Toast.LENGTH_LONG
        ).show()

        Navigation.findNavController(view).popBackStack()
      } catch (ex: Exception) {
        Toast.makeText(requireActivity().applicationContext, ex.message, Toast.LENGTH_LONG).show()
      }
    }

    servicesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        textViewSum.text = "${services[position].price} руб"
      }

      override fun onNothingSelected(parent: AdapterView<*>?) {
      }

    }

    super.onViewCreated(view, savedInstanceState)
  }
}