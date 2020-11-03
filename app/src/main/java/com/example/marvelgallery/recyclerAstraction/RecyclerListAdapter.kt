package com.example.marvelgallery.recyclerAstraction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class RecyclerListAdapter(var items: List<AnyItemAdapter> = listOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return items.first { it.layoutId == viewType }.onCreateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].bindViewHolder(holder)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].layoutId
}

typealias AnyItemAdapter = ItemAdapter<out RecyclerView.ViewHolder>