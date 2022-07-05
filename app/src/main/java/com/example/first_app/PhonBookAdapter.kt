package com.example.first_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhoneBookAdapter: RecyclerView.Adapter<PhoneBookAdapter.ViewHolder>(){

    var data = listOf<PhoneNumber>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.phone_number_item,
                parent, false)
        //val nameText = view.findViewById(R.id.name_text).text
        Log.i("ViewHolder", "ViewHolder Created $")
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.nameText.text = item.getName()
        holder.phoneNumber.text = item.getPhoneNumber()
        Log.i("ViewHolder", "ViewHolder Binded ${item.getName()}")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameText: TextView = itemView.findViewById(R.id.name_text)
        val phoneNumber: TextView = itemView.findViewById(R.id.phone_number)
        val dialButton: ImageView = itemView.findViewById(R.id.dial_button)
    }




}

