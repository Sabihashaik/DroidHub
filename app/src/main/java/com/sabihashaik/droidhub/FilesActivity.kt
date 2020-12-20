package com.sabihashaik.droidhub

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

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