package com.example.first_app

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.first_app.databinding.FragmentPostListBinding
import com.example.first_app.databinding.FragmentReadBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ReadFragment : Fragment() {
    var key : String ?= null
    private var mBinding: FragmentReadBinding?= null
    private val binding get() = mBinding!!
    val database = Firebase.database
    val myRef = database.getReference("board")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    // Context를 액티비티로 형변환해서 할당
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    //PostList data 받아오기
    fun getPostData(key : String){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val item = dataSnapshot.getValue(Model::class.java)
                Log.e("title: ", item!!.title)
                binding.tvTitle.text = item!!.title
                binding.tvBody.text = item!!.body
                binding.tvTime.text = item!!.time
                binding.tvEmail.text = item!!.email
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("PostListFragment", "Failed to read value.", error.toException())

            }
        }
        myRef.child(key).addValueEventListener(postListener)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentReadBinding.inflate(inflater, container, false)
        key = requireArguments().getString("key")
        Log.e("receive key: ", key.toString())
        getPostData(key.toString())
        // 삭제 구현
        binding.btnDelete.setOnClickListener{
            //database.getReference("board").child(key.toString()).setValue(null)
            val postListFragment = PostListFragment()
            requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, postListFragment, "postList")
                .commit();
            Toast.makeText(mainActivity, "삭제 완료", Toast.LENGTH_LONG).show()
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
            ReadFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}