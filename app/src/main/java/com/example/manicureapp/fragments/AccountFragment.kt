package com.example.manicureapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.manicureapp.R
import com.example.manicureapp.constants.AppConstants
import com.example.manicureapp.db.AppDatabase
import com.example.manicureapp.db.DbSingleton
import kotlinx.android.synthetic.main.account_fragment.*

class AccountFragment : Fragment() {

  private lateinit var db: AppDatabase

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    (activity as AppCompatActivity).supportActionBar!!.show()

    return inflater.inflate(R.layout.account_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    db = DbSingleton.getInstance(requireActivity().applicationContext)

    val sharedPreference = requireActivity().applicationContext.getSharedPreferences(
      AppConstants.SHARED_PREFS,
      Context.MODE_PRIVATE
    )
    val user = db.userDao().getById(sharedPreference.getInt(AppConstants.USER_UID, -1))

    textViewUsername.text = user.fullName
    editTextPhoneNumber.setText(user.phone)
    editTextEmail.setText(user.email)

    val order = db.orderDao().getByUserId(user.uid)

    if (order != null) {
      val service = db.serviceDao().getById(order.serviceUid)
      textViewOrder.text = "Запись на:\t${service.name}" +
        "\nДата:\t${order.date}" +
        "\nВремя:\t${order.time}" +
        "\nСтоимость:\t${service.price} руб."
    } else {
      textViewOrder.text = ""
    }

    super.onViewCreated(view, savedInstanceState)
  }
}