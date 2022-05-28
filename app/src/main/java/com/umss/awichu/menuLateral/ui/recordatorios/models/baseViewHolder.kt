package com.umss.awichu.menuLateral.ui.recordatorios.models

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class baseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView){
    abstract fun bind(item: T, position: Int)
}