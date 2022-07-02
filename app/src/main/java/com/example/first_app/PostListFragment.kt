package com.example.first_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.first_app.databinding.FragmentPostListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PostListFragment : Fragment() {
    private var mBinding: FragmentPostListBinding?= null
    private val binding get() = mBinding!!

    lateinit var postAdapter : PostAdapter
    val postList = mutableListOf<Model>()

    fun getData(){
        val database = Firebase.database
        val myRef = database.getReference("board")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //val post = dataSnapshot.getValue<Post>()
                for(dataModel in dataSnapshot.children){ // dataSnapshot 에 있는 정보를 받아오기
                    val item = dataModel.getValue(Model::class.java)
                    Log.d("PostListFragment", item.toString())
                    postList.add(item!!)
                }
                postAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("PostListFragment", "Failed to read value.", error.toException())
            }
        }
        myRef.addValueEventListener(postListener)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentPostListBinding.inflate(inflater, container, false)
        getData()
        postAdapter = PostAdapter(postList)
        binding.lvPost.adapter = postAdapter
        binding.btnWritePost.setOnClickListener{
            val writePostFragment = WritePostFragment()
            requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, writePostFragment, "writePost")
                .commit();
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}