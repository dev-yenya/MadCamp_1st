package com.example.first_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PhoneAdapter(val List : MutableList<Phone>) : BaseAdapter(){
    override fun getCount(): Int{
        return List.count()
    }
    override fun getItem(position: Int) : Any{
        return List[position]
    }

    override fun getItemId(position: Int): Long{
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if(view == null){
            view = LayoutInflater.from(parent?.context).inflate(R.layout.post_item, parent, false)
        }

        val name = view?.findViewById<TextView>(R.id.tv_name)
        name!!.text = List[position].name
        val number = view?.findViewById<TextView>(R.id.tv_number)
        number!!.text = List[position].number

        return view!!
    }

}