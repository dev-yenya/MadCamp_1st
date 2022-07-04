package com.example.first_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PostAdapter(val List : MutableList<Model>) : BaseAdapter(){
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

        val title = view?.findViewById<TextView>(R.id.tv_post)
        title!!.text = List[position].title
        val body = view?.findViewById<TextView>(R.id.tv_body)
        body!!.text = List[position].body
        val time = view?.findViewById<TextView>(R.id.tv_time)
        time!!.text = List[position].time
        val email = view?.findViewById<TextView>(R.id.tv_email)
        email!!.text = List[position].email

        return view!!
    }

}