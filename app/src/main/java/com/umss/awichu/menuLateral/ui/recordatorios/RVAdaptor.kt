package com.umss.awichu.menuLateral.ui.recordatorios

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.umss.awichu.R

class RVAdaptor(options: FirestoreRecyclerOptions<Note>) : FirestoreRecyclerAdapter <Note, RVAdaptor.RVViewHolder>(
    options
) {

    class RVViewHolder(intentView:View) : RecyclerView.ViewHolder(intentView){
        val noteText: TextView = itemView.findViewById(R.id.noteText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        return RVViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false))
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int, model: Note) {
        holder.noteText.text = model.text
    }
}