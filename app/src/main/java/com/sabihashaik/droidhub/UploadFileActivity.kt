package com.sabihashaik.droidhub

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.sabihashaik.droidhub.databinding.ActivityUploadFileBinding


class UploadFileActivity : AppCompatActivity() {

    companion object{
        private val PICK_IMAGE_CODE=1000
    }

    lateinit var binding: ActivityUploadFileBinding
    var filePath: Uri? = null
    private var mStorageRef: StorageReference? = null
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_CODE && resultCode== RESULT_OK && data!=null){
            filePath = data.data!!
            binding.FilePathText.text = "File Path: "+filePath
            Log.d("DroidHub", "File Path: " + filePath)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

      super.onCreate(savedInstanceState)


        mStorageRef = Firebase.storage.getReference();
        binding = ActivityUploadFileBinding.inflate(layoutInflater)
        auth = Firebase.auth
        db = Firebase.firestore

        binding.fileChooseButton.setOnClickListener{
           fileChooser()
        }

        binding.fileUploadButton.setOnClickListener{
            uploadFile()
        }
        setContentView(binding.root)

    }

    private fun fileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select an Image File"), PICK_IMAGE_CODE)
    }

    private fun uploadFile() {
        val fileName = binding.fileNameField.text.toString()
        val myFileRef = mStorageRef?.child("images/" + fileName)
        val uploadTask = filePath?.let { myFileRef?.putFile(it) }

        uploadTask?.addOnFailureListener {
            Toast.makeText(this, "Task Failed" + it, Toast.LENGTH_SHORT).show()
         }?.addOnSuccessListener { taskSnapshot ->

            val result: Task<Uri> = taskSnapshot.storage.downloadUrl
            result.addOnSuccessListener { uri ->
                val downloadUri = uri.toString()
                addItemtoFireStore(fileName,downloadUri)
            }

            Toast.makeText(this, "Task Succeeded" , Toast.LENGTH_SHORT).show()
        }

    }

    private fun addItemtoFireStore(fileName: String, downloadUri: String) {
        Log.d("DroidHub", "Entered Adding Item to Firestore")
        val userId = auth.uid.toString()
        //content://com.android.providers.media.documents/document/image%3A63996
        var downloadUri2 = downloadUri.toString()
        var docs = hashMapOf(
                "downloadURL" to downloadUri2,
                "filename" to fileName
        )
        //val collectionPath = "users/"+userId+"/documents"

        try {
            db.collection("users").document(userId)
                    .collection("documents").document()
                    .set(docs, SetOptions.merge())
                    .addOnSuccessListener { documentReference ->
                        Log.d("DroidHub", "DocumentSnapshot added with ID: ${documentReference}")
                    }
                    .addOnFailureListener { e ->
                        Log.d("DroidHub", "Error adding document", e)
                    }
        }
        catch (e: Exception){
            Log.d("DroidHub", "Oops!" + e)
        }
    }


}