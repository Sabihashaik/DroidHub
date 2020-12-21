package com.sabihashaik.droidhub

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.sabihashaik.droidhub.databinding.ActivityUploadFileBinding
import java.io.File


class UploadFileActivity : AppCompatActivity() {

    companion object{
        private val PICK_IMAGE_CODE=1000
    }

    lateinit var binding: ActivityUploadFileBinding
    var filePath: Uri? = null
    private var mStorageRef: StorageReference? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_CODE && resultCode== RESULT_OK && data!=null){
            filePath = data.data!!
            binding.FilePathText.text = "File Path: "+filePath
            Log.d("DroidHub","File Path: "+filePath)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

      super.onCreate(savedInstanceState)

        mStorageRef = Firebase.storage.getReference();
        binding = ActivityUploadFileBinding.inflate(layoutInflater)

        binding.fileChooseButton.setOnClickListener{
           fileChooser()
        }

        binding.fileUploadButton.setOnClickListener{
            uploadFile()
        }
        setContentView(binding.root)

    }

    private fun fileChooser() {
        var intent = Intent()
        intent.type = "image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select an Image File"), PICK_IMAGE_CODE)
    }

    private fun uploadFile() {
        var fileName = binding.fileNameField.text.toString()
        val myFileRef = mStorageRef?.child("images/"+fileName)
        var uploadTask = filePath?.let { myFileRef?.putFile(it) }

        uploadTask?.addOnFailureListener {
            Toast.makeText(this, "Task Failed"+it, Toast.LENGTH_SHORT).show()
         }?.addOnSuccessListener { taskSnapshot ->
            Toast.makeText(this, "Task Succeeded"+taskSnapshot, Toast.LENGTH_SHORT).show()
        }

    }


}