package com.example.first_app

import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.example.first_app.databinding.ActivityImageDetailBinding

class ImageDetailActivity : AppCompatActivity() {
    private var binding: ActivityImageDetailBinding = ActivityImageDetailBinding.inflate(layoutInflater)
    lateinit var datas : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_image_detail)
            datas = intent.getSerializableExtra("data") as Uri
            Glide.with(this).load(datas).into(binding.imgDetail)
    }

}