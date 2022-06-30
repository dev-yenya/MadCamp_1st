package com.example.first_app

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.first_app.databinding.MultiImageItemBinding


/*class MultiImageAdapter(private val items: ArrayList<Uri>, val context:Context)

}*/

class MultiImageAdapter(val context:Context) : RecyclerView.Adapter<MultiImageAdapter.ViewHolder>(){
    var datas = mutableListOf<Photo>()
        /*RecyclerView.Adapter<MultiImageAdapter.ViewHolder>(){
            override fun getItemCount() : Int = items.size

        }*/

    override fun getItemCount(): Int{
        Log.e("size", datas.size.toString())
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(datas[position])
    }

    inner class ViewHolder(val binding: MultiImageItemBinding):RecyclerView.ViewHolder(binding.root){
       fun bind(item : Photo){
           binding.tvImage.text = item.name
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MultiImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}

