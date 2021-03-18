package com.example.firebaselogin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(val data: ArrayList<Phone>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    // we need layout for each element -- so here we need a layout that can be used for each element in the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // this is the item_list we created from (layout resources)
        val tv = inflater.inflate(R.layout.item_list, parent, false) as TextView

        tv.setOnClickListener {
            Toast.makeText(parent.context, tv.text, Toast.LENGTH_SHORT).show()
        }

        return ViewHolder(tv)
    }

    // when the previous is created we need to bind it in below so we can see it , and here we will put the data from the database to our viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView as TextView
        holder.itemView.text = data[position].toString()
        holder.itemView.setOnLongClickListener{

            data.removeAt(position)
            notifyDataSetChanged()

            true
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun add(phone: Phone){
        data.add(phone)
        notifyItemInserted(getItemCount() - 1)
    }

}
