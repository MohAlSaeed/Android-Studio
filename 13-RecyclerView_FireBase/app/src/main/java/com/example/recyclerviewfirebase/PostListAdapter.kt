package com.example.recyclerviewfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*

class PostListAdapter(var postlistItems: List<Phones>) : RecyclerView.Adapter<PostListAdapter.ViewHolder>(){


// create a view holder for the items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(phones: Phones){
            itemView.tv1.text = phones.name
            itemView.tv2.text = phones.price.toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostListAdapter.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(postlistItems[position])
    }

    override fun getItemCount(): Int {
        return postlistItems.size
    }
}