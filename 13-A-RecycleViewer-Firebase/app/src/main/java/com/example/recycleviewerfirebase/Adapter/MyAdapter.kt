package com.example.recycleviewerfirebase.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewerfirebase.Model.Phone
import com.example.recycleviewerfirebase.R
import kotlinx.android.synthetic.main.phone_layout_item.view.*

class MyAdapter (internal var context:Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    internal var phoneList: MutableList<Phone>

    fun addAll(newPhone:List<Phone>){
        val init: Int = phoneList.size
        notifyItemRangeChanged(init, newPhone.size)
    }

    fun removeLastItem(){
        phoneList.removeAt(phoneList.size-1)
    }

    init {
        this.phoneList = ArrayList()
    }

    inner class MyViewHolder(intemView: View): RecyclerView.ViewHolder(intemView) {
    internal var name: TextView
    internal var price: TextView

    init {
        name = intemView.findViewById<TextView>(R.id.tv1)
        price = intemView.findViewById<TextView>(R.id.tv2)
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val intemView: View = LayoutInflater.from(context).inflate(R.layout.phone_layout_item, parent,false)
        return MyViewHolder(intemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = phoneList[position].name
        holder.price.text = phoneList[position].price
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }
}