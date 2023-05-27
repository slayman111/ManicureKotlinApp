package com.example.manicureapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.manicureapp.R
import com.example.manicureapp.constants.AppConstants
import com.example.manicureapp.db.entities.Service

class ServicesAdapter(
  private val view: View,
  private val services: List<Service>,
) : RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val cardViewService: CardView
    val textViewService: TextView

    init {
      cardViewService = view.findViewById(R.id.cardViewService)
      textViewService = view.findViewById(R.id.textViewServiceName)
    }
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(viewGroup.context)
      .inflate(R.layout.service_recycler_item, viewGroup, false)

    return ViewHolder(view)
  }

  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    viewHolder.textViewService.text = services[position].name

    viewHolder.cardViewService.setOnClickListener {
      val bundle = Bundle()
      bundle.putInt(AppConstants.SERVICE_UID, services[position].uid)

      Navigation.findNavController(view)
        .navigate(R.id.action_priceListFragment_to_serviceInfoFragment, bundle)
    }
  }

  override fun getItemCount() = services.size
}