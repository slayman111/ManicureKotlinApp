package com.example.manicureapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.manicureapp.R
import com.example.manicureapp.constants.AppConstants
import com.example.manicureapp.db.AppDatabase
import com.example.manicureapp.db.DbSingleton
import kotlinx.android.synthetic.main.authorization_fragment.*


class AuthorizationFragment : Fragment() {

  private lateinit var db: AppDatabase

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

    return inflater.inflate(R.layout.authorization_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    db = DbSingleton.getInstance(requireActivity().applicationContext)

    buttonSignIn.setOnClickListener {
      try {
        val user = db.userDao()
          .getByPhoneAndPassword(editTextPhone.text.toString(), editTextPassword.text.toString())

        if (user == null) throw Exception("Неправильный логин или пароль")

        val bundle = Bundle()
        bundle.putInt(AppConstants.USER_UID, user.uid)

        val sharedPreference = requireActivity().applicationContext.getSharedPreferences(
          AppConstants.SHARED_PREFS,
          Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putInt(AppConstants.USER_UID, user.uid)
        editor.apply()

        Navigation.findNavController(view)
          .navigate(R.id.action_authorizationFragment_to_mastersFragment, bundle)
      } catch (ex: Exception) {
        Toast.makeText(requireActivity().applicationContext, ex.message, Toast.LENGTH_LONG).show()
      }
    }

    textViewRegistration.setOnClickListener {
      Navigation.findNavController(view)
        .navigate(R.id.action_authorizationFragment_to_registrationFragment)
    }

    super.onViewCreated(view, savedInstanceState)
  }
}