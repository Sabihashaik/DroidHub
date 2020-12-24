package com.sabihashaik.droidhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sabihashaik.droidhub.databinding.ActivityFilesBinding

class FileDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityFilesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilesBinding.inflate(layoutInflater)


        setContentView(binding.root)


    }
}