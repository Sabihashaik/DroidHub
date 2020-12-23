package com.sabihashaik.droidhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.sabihashaik.droidhub.adapter.FileListAdapter
import com.sabihashaik.droidhub.model.fileItem
import kotlinx.android.synthetic.main.content_files.*


class FilesActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    var fileListAdapter: FileListAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files)
        auth = Firebase.auth
        setUpRecyclerView()
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val uploadFileIntent = Intent(this, UploadFileActivity::class.java)
             startActivity(uploadFileIntent)

        }
    }

    private fun setUpRecyclerView() {
        val userId = auth.uid.toString()


        val query= FirebaseFirestore.getInstance()
            .collection("users")
            .document(userId)
            .collection("documents")

        val options: FirestoreRecyclerOptions<fileItem> = FirestoreRecyclerOptions.Builder<fileItem>()
            .setQuery(query, fileItem::class.java)
            .build()

        fileListAdapter = FileListAdapter(options)
        fileRecylerView.layoutManager = LinearLayoutManager(this)
        fileRecylerView.adapter = fileListAdapter
    }

    override fun onStart() {
        super.onStart()
        fileListAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        fileListAdapter!!.stopListening()
    }
    override fun onStop() {
        super.onStop()
        fileListAdapter!!.stopListening()
    }


}