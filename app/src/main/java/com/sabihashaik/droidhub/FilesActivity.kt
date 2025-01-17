package com.sabihashaik.droidhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.sabihashaik.droidhub.adapter.FileListAdapter
import com.sabihashaik.droidhub.adapter.FileListAdapter.OnItemClickListener
import com.sabihashaik.droidhub.model.fileItem
import kotlinx.android.synthetic.main.content_files.*
import java.io.File


class FilesActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var auth: FirebaseAuth
    private var storageRef: StorageReference? = null
    private lateinit var db: FirebaseFirestore
    var fileListAdapter: FileListAdapter?=null
    private lateinit var userId:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files)
        auth = Firebase.auth
        userId = auth.uid.toString()

        setUpRecyclerView()
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val uploadFileIntent = Intent(this, UploadFileActivity::class.java)
             startActivity(uploadFileIntent)

        }
    }

    private fun setUpRecyclerView() {


        val query= FirebaseFirestore.getInstance()
            .collection("users")
            .document(userId)
            .collection("documents")

        val options: FirestoreRecyclerOptions<fileItem> = FirestoreRecyclerOptions.Builder<fileItem>()
            .setQuery(query, fileItem::class.java)
            .build()

        fileListAdapter = FileListAdapter(this,options)
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

    override fun onItemClick(position: Int) {

    }
}