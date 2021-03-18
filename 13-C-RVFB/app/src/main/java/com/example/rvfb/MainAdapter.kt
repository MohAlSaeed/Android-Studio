package com.example.rvfb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter(private val context: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var datalist = mutableListOf<Phones>()

    fun setListData(data: MutableList<Phones>){
        datalist = data
    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView : View = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val ph = datalist[position]
        holder.bindView(ph)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(ph:Phones){
            itemView.tv1.text = ph.name
            itemView.tv2.text = ph.price
            itemView.tv3.text = ph.quantity
            itemView.rb.isChecked = ph.available.toBoolean()
        }

    }

}
