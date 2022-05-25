package com.umss.awichu.menuLateral.ui.recordatorios.models

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umss.awichu.R



class RemainAdapter(private val remainingList: ArrayList<Remaining>): RecyclerView.Adapter<RemainAdapter.MyViewHolder1>(){


    class MyViewHolder1(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameRemain1: TextView = itemView.findViewById(R.id.etx_nameItem)
        val dateRemain1: TextView = itemView.findViewById(R.id.etx_dateItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return MyViewHolder1(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {
        val currentItem = remainingList[position]

        holder.nameRemain1.text = currentItem.nombre
        holder.dateRemain1.text = currentItem.fechaRecordatorio
    }

    override fun getItemCount(): Int {
        return  remainingList.size
    }

}