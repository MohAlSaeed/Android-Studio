package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val myInflater = LayoutInflater.from(parent.context)
        val tv = myInflater.inflate(R.layout.text_view, parent, false)
        return ViewHolder(tv)
        
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView as TextView
        holder.itemView.text = data.get(position)
    }

    override fun getItemCount(): Int = data.size

}