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
// 여기서 dataSet의 형식은 본인이 서버에서 데이터를 GET해서 오는 것이라면 reponsedata를, 직접 만든 데이터클래스를 사용하고 싶으면 dataclass의 이름을 넣으면 된다.
class MultiImageAdapter(val context:Context) : RecyclerView.Adapter<MultiImageAdapter.ViewHolder>(){
    var datas = mutableListOf<Photo>()
        /*RecyclerView.Adapter<MultiImageAdapter.ViewHolder>(){
            override fun getItemCount() : Int = items.size

        }*/

    // 총 몇개의 반복되는 뷰 데이터가 있는지
    override fun getItemCount(): Int{
        Log.d("size", datas.size.toString())
        return datas.size
    }

    // 여기서 뷰와 데이터의 결합
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(datas[position])
    }

    inner class ViewHolder(val binding: MultiImageItemBinding):RecyclerView.ViewHolder(binding.root){
       fun bind(item : Photo){
           binding.tvImage.text = item.name
           //binding.ivImage.src = item.name
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MultiImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}

