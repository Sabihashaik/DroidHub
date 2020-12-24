package com.sabihashaik.droidhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.sabihashaik.droidhub.databinding.ActivityFileDetailBinding
import com.sabihashaik.droidhub.databinding.ActivityFilesBinding

class FileDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityFileDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFileDetailBinding.inflate(layoutInflater)

        val intent:Intent = getIntent()
        val fileName:String= intent.getStringExtra("fileName").toString()
        val downloadURL:String= intent.getStringExtra("downloadURL").toString()

        binding.yourFileName.text = fileName

        Log.d("DroidHub","URL:"+downloadURL)

        Glide.with(this)
            .load(downloadURL)
            .into(binding.yourImageView);

        setContentView(binding.root)

    }
}