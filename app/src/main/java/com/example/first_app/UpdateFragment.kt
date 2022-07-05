package com.example.first_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.first_app.databinding.FragmentUpdateBinding
import com.example.first_app.databinding.FragmentWritePostBinding

class UpdateFragment : Fragment() {
    private var mBinding: FragmentUpdateBinding?= null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
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