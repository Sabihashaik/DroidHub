package com.sabihashaik.droidhub.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.sabihashaik.droidhub.model.fileItem
import com.sabihashaik.droidhub.FilesActivity
import com.sabihashaik.droidhub.R
import kotlinx.android.synthetic.main.item_layout.view.*



class FileListAdapter( val listener: OnItemClickListener, options: FirestoreRecyclerOptions<fileItem>) :
    FirestoreRecyclerAdapter<fileItem, FileListAdapter.FileAdapterViewHolder>(options) {

    inner class FileAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),   View.OnClickListener {
        val fileName: TextView = itemView.fileNameTextView


         init {
             itemView.setOnClickListener(this)
         }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            listener.onItemClick(position)
            Log.d("DroidHub","postion is"+position)
        }

    }

    override fun onBindViewHolder(holder: FileListAdapter.FileAdapterViewHolder, position: Int, model: fileItem) {
        holder.fileName.text = model.filename

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileListAdapter.FileAdapterViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout
                ,parent,false)
        return FileAdapterViewHolder(itemView)
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)

    }

}
