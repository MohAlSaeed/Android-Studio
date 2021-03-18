package com.mt.letschatmt

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chat.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter(private val context: Context, private val mainViewModel: MainViewModel) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var myDataList = mutableListOf<MyData>()   // this is an empty DataList

    fun setmyDataList(data: MutableList<MyData>){
        myDataList = data
        notifyDataSetChanged()
    }


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(data: MyData){
            itemView.rtv1.text = data.desc
            itemView.rtv2.text = data.email
        }
    }


    // to implement the below
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val myview : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return MainViewHolder(myview)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val oneDatarow: MyData = myDataList[position]
        holder.bindView(oneDatarow)
        // delete one row data
        holder.itemView.rcl1.setOnClickListener {
            mainViewModel.deleteData(oneDatarow)
            notifyDataSetChanged()
//            mainViewModel.fetchData().observeForever {
//                setmyDataList(it)
//            }
        }

        holder.itemView.rtv1.setOnLongClickListener {
            mainViewModel.deleteData(oneDatarow)
            mainViewModel.fetchData().observeForever {
                setmyDataList(it)
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return myDataList.size
    }


}
