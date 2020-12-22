package com.sabihashaik.droidhub

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FilesActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val uploadFileIntent = Intent(this, UploadFileActivity::class.java)
             startActivity(uploadFileIntent)

        }
    }
}