package com.example.manicureapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.manicureapp.R
import com.example.manicureapp.db.AppDatabase
import com.example.manicureapp.db.DbSingleton
import com.example.manicureapp.db.entities.User
import kotlinx.android.synthetic.main.registration_fragment.*

class RegistrationFragment : Fragment() {

  private lateinit var db: AppDatabase

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

    return inflater.inflate(R.layout.registration_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    db = DbSingleton.getInstance(requireActivity().applicationContext)

    imageButtonBack.setOnClickListener {
      Navigation.findNavController(view).popBackStack()
    }

    buttonRegister.setOnClickListener {
      try {
        if (editTextFullName.text.toString().isEmpty() ||
          editTextEmail.text.toString().isEmpty() ||
          editTextPhoneNumber.text.toString().isEmpty() ||
          editTextPassword.text.toString().isEmpty()
        ) {
          throw Exception("Заполните все поля")
        }

        db.userDao().insertAll(User(
          editTextFullName.text.toString(),
          editTextEmail.text.toString(),
          editTextPhoneNumber.text.toString(),
          editTextPassword.text.toString(),
        ))

        Toast.makeText(
          requireActivity().applicationContext,
          "Ваш аккаунт успешно зарегистрирован",
          Toast.LENGTH_LONG
        ).show()

        Navigation.findNavController(view).popBackStack()
      } catch (ex: Exception) {
        Toast.makeText(requireActivity().applicationContext, ex.message, Toast.LENGTH_LONG).show()
      }
    }

    super.onViewCreated(view, savedInstanceState)
  }
}