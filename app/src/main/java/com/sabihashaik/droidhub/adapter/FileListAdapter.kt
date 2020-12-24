package com.sabihashaik.droidhub.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.sabihashaik.droidhub.FileDetailActivity
import com.sabihashaik.droidhub.model.fileItem
import com.sabihashaik.droidhub.FilesActivity
import com.sabihashaik.droidhub.R
import kotlinx.android.synthetic.main.item_layout.view.*



class FileListAdapter( val listener: OnItemClickListener, options: FirestoreRecyclerOptions<fileItem>) :
    FirestoreRecyclerAdapter<fileItem, FileListAdapter.FileAdapterViewHolder>(options) {

    inner class FileAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),   View.OnClickListener {
        val fileName: TextView = itemView.fileNameTextView
        val documentURL: TextView = itemView.documentDownloadURL

         init {
             itemView.setOnClickListener(this)
         }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            listener.onItemClick(position)
            try {

                val detailIntent = Intent(p0?.context, FileDetailActivity::class.java)
                detailIntent.putExtra("fileName", fileName.text)
                detailIntent.putExtra("downloadURL", documentURL.text)
                startActivity(p0!!.context, detailIntent, null)
            }
            catch(e:Exception){
                Log.d("DroidHub","Error is "+e)
            }
         }

    }

    override fun onBindViewHolder(holder: FileListAdapter.FileAdapterViewHolder, position: Int, model: fileItem) {
        holder.fileName.text = model.filename
        holder.documentURL.text = model.downloadURL

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
