package com.example.first_app

import android.content.Context
import android.content.Intent
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
    private val postKeyList = mutableListOf<String>()

    //PostList data 받아오기
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
                    postKeyList.add(dataModel.key.toString())
                }
                postAdapter.notifyDataSetChanged()
                postList.reverse() // 최신순
                postKeyList.reverse() // 최신순
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
        binding.lvPost.setOnItemClickListener { parent, view, position, id ->
            val readPostFragment = ReadFragment()
            //fragment에 데이터 전달하기
            val bundle = Bundle()
            bundle.putString("key", postKeyList[position])
            readPostFragment.arguments = bundle
            Log.e("send key : ", postKeyList[position])
            requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, readPostFragment, "readPost")
                .commit();
        }
        return binding.root
    }

    override fun onDestroyView() {
        //onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroyView()
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