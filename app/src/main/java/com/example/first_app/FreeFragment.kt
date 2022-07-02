package com.example.first_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.first_app.databinding.FragmentFreeBinding
import com.example.first_app.databinding.FragmentGalleryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FreeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FreeFragment : Fragment() {
    private var mBinding: FragmentFreeBinding?= null
    private val binding get() = mBinding!!
    lateinit var postAdapter : PostAdapter
    val postList = mutableListOf<Model>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFreeBinding.inflate(inflater, container, false)
        binding.btnWrite.setOnClickListener{

            val writeText = binding.etWrite.text
            val database = Firebase.database
            val myRef = database.getReference("board")

            myRef.push().setValue(
                Model(writeText.toString())
             )
        }
        getData()
        postAdapter = PostAdapter(postList)
        binding.lvPost.adapter = postAdapter
        return binding.root
    }

    fun getData(){
        val database = Firebase.database
        val myRef = database.getReference("board")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //val post = dataSnapshot.getValue<Post>()
                for(dataModel in dataSnapshot.children){ // dataSnapshot 에 있는 정보를 받아오기
                    val item = dataModel.getValue(Model::class.java)
                    Log.d("FreeFragment", item.toString())
                    postList.add(item!!)
                }
                postAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("FreeFragment", "Failed to read value.", error.toException())
            }
        }
        myRef.addValueEventListener(postListener)

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FreeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
    override fun onDestroyView() {
        //onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroyView()
    }
}