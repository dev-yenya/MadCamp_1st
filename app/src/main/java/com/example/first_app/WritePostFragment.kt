package com.example.first_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView




import com.example.first_app.databinding.FragmentWritePostBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class WritePostFragment : Fragment() {
    private var mBinding: FragmentWritePostBinding?= null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentWritePostBinding.inflate(inflater, container, false)
        binding.btnWrite.setOnClickListener{

            val writeTitle = binding.etTitle.text
            val writeBody = binding.etBody.text
            val writeTime = FBAuth.getTime()
            val writeUid = FBAuth.getUid()
            val writeEmail = FBAuth.getEmail()
            val database = Firebase.database
            val myRef = database.getReference("board")

            myRef.push().setValue(
                Model(writeTitle.toString(), writeBody.toString(), writeUid, writeTime, writeEmail)
             )

            val postListFragment = PostListFragment()
            requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, postListFragment, "postList")
                .commit();

        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WritePostFragment().apply {
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