package com.umss.awichu.menuLateral.ui.recordatorios.models

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umss.awichu.R
import com.umss.awichu.botonesInferiores.BotonRecordatorios
import kotlinx.android.synthetic.main.item_rv.view.*


class RemainAdapter(private val remainingList: ArrayList<Remaining>,
                    private val itemClickListenner: BotonRecordatorios
): RecyclerView.Adapter<baseViewHolder<*>>() {

    interface onRemainClickListenner {
        fun onRemainClick(nombre: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): baseViewHolder<*> {
        return remainingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false))
    }

    override fun onBindViewHolder(holder: baseViewHolder<*>, position: Int) {
        when (holder) {
            is remainingViewHolder -> holder.bind(remainingList[position], position)
            else -> throw IllegalArgumentException("Error")
        }
    }

    override fun getItemCount(): Int {
        return remainingList.size
    }

    inner class remainingViewHolder(itemView: View) : baseViewHolder<Remaining>(itemView) {
        override fun bind(item: Remaining, position: Int) {
            itemView.setOnClickListener { itemClickListenner.onRemainClick(item.nombre) }
            itemView.etx_nameItem.text = item.nombre
            itemView.etx_dateItem.text = item.fechaRecordatorio
            itemView.etx_hourItem.text = item.hora

        }

    }
}