package com.example.phonestock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycleview_row.view.*

class RVAdapter() : RecyclerView.Adapter<RVAdapter.RVAdapterViewHolder>() {


    var items = ArrayList<Phones>()
    fun setListData (data : ArrayList<Phones>){
        this.items = data
    }



    class RVAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // here you have a mistake in your previous project
        // here you have a mistake in your previous project
        // here you have a mistake in your previous project
        // here you have a mistake in your previous project
        // here you have a mistake in your previous project
//        val rvtv1 = view.rvtv1
//        fun bind(title: String){
//            rvtv1.text = title
////            view.rvtv2 = title
//        }
    }



//// below methods to be implemented

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterViewHolder {
        // we inflate layout from the linearlayout we created for a single row (recycleview_row)
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_row,parent,false)
        return RVAdapterViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RVAdapterViewHolder, position: Int) {
//        holder.bind(items[position])
        holder.itemView.rvtv1.text = items[position].name
        holder.itemView.rvtv2.text = items[position].price.toString()
        holder.itemView.rvtv3.text = items[position].quantity.toString()
        holder.itemView.rvrb.isChecked = items[position].available
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
