package com.example.manicureapp.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.manicureapp.R
import com.example.manicureapp.constants.AppConstants
import com.example.manicureapp.db.entities.Master

class MastersAdapter(
  private val context: Context,
  private val view: View,
  private val userUid: Int,
  private val masters: List<Master>,
) : RecyclerView.Adapter<MastersAdapter.ViewHolder>() {

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageViewAvatar: ImageView
    val textViewFullName: TextView
    val textViewSkills: TextView
    val buttonSignUp: Button

    init {
      imageViewAvatar = view.findViewById(R.id.imageViewMaster)
      textViewFullName = view.findViewById(R.id.textViewMasterName)
      textViewSkills = view.findViewById(R.id.textViewSkills)
      buttonSignUp = view.findViewById(R.id.buttonSignUp)
    }
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(viewGroup.context)
      .inflate(R.layout.master_recycler_item, viewGroup, false)

    return ViewHolder(view)
  }

  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    viewHolder.textViewFullName.text = masters[position].fullName
    viewHolder.textViewSkills.text = masters[position].specialities

    viewHolder.imageViewAvatar.setImageResource(
      context.resources.getIdentifier(
        "drawable/${masters[position].image}",
        "drawable",
        context.packageName
      )
    )

    viewHolder.buttonSignUp.setOnClickListener {
      val bundle = Bundle()
      bundle.putInt(AppConstants.USER_UID, userUid)

      Navigation.findNavController(view)
        .navigate(R.id.action_mastersFragment_to_signUpFragment, bundle)
    }
  }

  override fun getItemCount() = masters.size
}