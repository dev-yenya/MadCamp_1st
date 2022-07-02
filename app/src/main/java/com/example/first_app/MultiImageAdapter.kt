package com.example.first_app

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


/*class MultiImageAdapter(private val items: ArrayList<Uri>, val context:Context)

}*/
// 여기서 dataSet의 형식은 본인이 서버에서 데이터를 GET해서 오는 것이라면 reponsedata를, 직접 만든 데이터클래스를 사용하고 싶으면 dataclass의 이름을 넣으면 된다.
class MultiImageAdapter(private val items: ArrayList<Uri>,val context:Context) :
    RecyclerView.Adapter<MultiImageAdapter.ViewHolder>(){
    //var datas = mutableListOf<Photo>()
    interface OnItemClickListener{
        fun onItemClick(v:View, data:Uri, pos:Int)
    }
    private var listener: OnItemClickListener ?= null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    // 총 몇개의 반복되는 뷰 데이터가 있는지
    override fun getItemCount(): Int{
        Log.d("size", items.size.toString())
        return items.size
    }

    // 여기서 뷰와 데이터의 결합
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val item = items[position]
       Glide.with(context).load(item)
           .override(600, 140)
           .into(holder.image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.multi_image_item, parent, false)
        return ViewHolder(inflatedView)
    }
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        private var view: View = v
        var image = v.findViewById<ImageView>(R.id.iv_image)
        //fun bind(listener: View.OnClickListener, item: String){
        fun bind(item: Uri){
            //view.setOnClickListener(listener)d
            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,item,pos)
                }
            }
        }
    }



    }




