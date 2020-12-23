package com.sabihashaik.droidhub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.sabihashaik.droidhub.model.fileItem
import com.sabihashaik.droidhub.FilesActivity
import com.sabihashaik.droidhub.R
import kotlinx.android.synthetic.main.item_layout.view.*



class FileListAdapter(options: FirestoreRecyclerOptions<fileItem>) :
    FirestoreRecyclerAdapter<fileItem, FileListAdapter.FileAdapterViewHolder>(options) {

    class FileAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fileName: TextView = itemView.fileNameTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileAdapterViewHolder {
       // return FileAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
        val itemView= LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout
            ,parent,false)
        return FileAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FileAdapterViewHolder, position: Int, model: fileItem) {

        holder.fileName.text = model.filename
    }

}