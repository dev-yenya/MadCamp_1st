package com.example.first_app

import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.first_app.databinding.FragmentPhoneBookBinding
import org.json.JSONArray
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.AsynchronousServerSocketChannel.open


class PhoneBookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentPhoneBookBinding>(inflater, R.layout.fragment_phone_book, container, false)

        val viewModel = ViewModelProvider(this).get(PhoneBookViewModel::class.java)

        binding.phoneBookViewModel = viewModel

        val adapter = PhoneBookAdapter()

        binding.phoneNumberList.adapter = adapter

        adapter.data = viewModel.phoneNumberList


        /*val jsonString = assets.open("phoneNumbers.json").reader().readText()
        val jsonArray = JSONArray(jsonString)
        val jsonObject = jsonArray.getJSONObject()*/
        return binding.root
    }

}