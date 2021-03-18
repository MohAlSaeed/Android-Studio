package com.example.recycler_view_modify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdabter(private val data: ArrayList<String>) : RecyclerView.Adapter<MyAdabter.ViewHolder>() {
    // in Kotlin (:) means extends as here in below line and because this class type (RecyclerView implements ViewHolder) which needs an input data
    class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdabter.ViewHolder {
        // we will make a val which hold a layout template from parent
        val myInflater: LayoutInflater = LayoutInflater.from(parent.context)
        // now here parent to be the parent of the generated layout (text_view) which represents an empty text_view layout resources file in your package
        val tv = myInflater.inflate(R.layout.text_view, parent,false) as TextView
        tv.setOnClickListener{
            Toast.makeText(parent.context, tv.text, Toast.LENGTH_SHORT).show()
        }

        return ViewHolder(tv)
    }

    override fun onBindViewHolder(holder: MyAdabter.ViewHolder, position: Int) {
        holder.itemView as TextView
        holder.itemView.text = data.get(position)
        holder.itemView.setOnLongClickListener{
            data.removeAt(position)
            // to notify the RecyclerView that the data has been changed
            notifyDataSetChanged()
            true
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun add(str: String){
        data.add(str)
        notifyItemInserted(getItemCount()-1)
    }

}
