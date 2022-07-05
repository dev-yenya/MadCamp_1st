package com.example.first_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.first_app.databinding.FragmentUpdateBinding
import com.example.first_app.databinding.FragmentWritePostBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UpdateFragment : Fragment() {
    private var mBinding: FragmentUpdateBinding?= null
    private val binding get() = mBinding!!
    val database = Firebase.database
    val myRef = database.getReference("board")
    var key : String ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    fun getPostData(key : String){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val item = dataSnapshot.getValue(Model::class.java)
                if(item != null){
                    binding.etTitle.setText(item!!.title)
                    binding.etBody.setText(item!!.body)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("UpdateFragment", "Failed to read value.", error.toException())

            }
        }
        myRef.child(key).addValueEventListener(postListener)

    }

    private fun editPostData(key: String){
        val updateTitle = binding.etTitle.text
        val updateBody = binding.etBody.text
        val database = Firebase.database
        val myRef = database.getReference("board")
        val post = Model(updateTitle.toString(), updateBody.toString(), FBAuth.getUid(), FBAuth.getTime(), FBAuth.getEmail())
        myRef.child(key).setValue(post)
        val readPostFragment = ReadFragment()
        val bundle = Bundle()
        bundle.putString("key", key.toString())
        Log.e("send key :ab ", key.toString())
        readPostFragment.arguments = bundle
        requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.fl_container, readPostFragment, "readPost")
            .commit()
        /*val postListFragment = PostListFragment()
        requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.fl_container, postListFragment, "postList")
            .commit()*/
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        key = requireArguments().getString("key")
        getPostData(key.toString())

        binding.btnUpdate.setOnClickListener{
            editPostData(key.toString())
        }
        return binding.root
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            UpdateFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}