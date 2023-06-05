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
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SignUpFragment : Fragment() {

  private lateinit var db: AppDatabase
  private lateinit var services: List<Service>
  private lateinit var masters: List<Master>
  private val calendar: Calendar = Calendar.getInstance()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    (activity as AppCompatActivity).supportActionBar!!.show()

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

    val datesArrayAdapter: ArrayAdapter<String> =
      ArrayAdapter<String>(
        requireActivity().applicationContext,
        android.R.layout.simple_spinner_item,
        getNextWeek()
      )
    dateSpinner.adapter = datesArrayAdapter

    val timesArrayAdapter: ArrayAdapter<String> =
      ArrayAdapter<String>(
        requireActivity().applicationContext,
        android.R.layout.simple_spinner_item,
        getWorkTime()
      )
    timesSpinner.adapter = timesArrayAdapter

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
        db.orderDao().insertAll(Order(
          masters[mastersSpinner.selectedItemPosition].uid,
          services[servicesSpinner.selectedItemPosition].uid,
          userUid!!,
          dateSpinner.selectedItem.toString(),
          timesSpinner.selectedItem.toString()
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

  private fun getNextWeek(): ArrayList<String> {
    val format: DateFormat = SimpleDateFormat("MM.dd")
    val days = ArrayList<String>()
    for (i in 0..6) {
      days.add(format.format(this.calendar.getTime()))
      this.calendar.add(Calendar.DATE, 1)
    }
    return days
  }

  private fun getWorkTime(): ArrayList<String> {
    val time = ArrayList<String>()

    with(time) {
      add("10:00")
      add("11:00")
      add("12:00")
      add("13:00")
      add("14:00")
      add("15:00")
      add("16:00")
      add("17:00")
    }

    return time
  }
}