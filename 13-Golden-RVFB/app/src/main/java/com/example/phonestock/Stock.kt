package com.example.phonestock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_stock.*
import kotlinx.android.synthetic.main.activity_stock.view.*

class Stock : AppCompatActivity() {

    lateinit var myrecycleViewAdapter: RVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        // to set or initiat our recyclerview
        initRV()
        createData()


    }









    private fun initRV(){
        rv.apply {
            layoutManager =  LinearLayoutManager(this.context)
            myrecycleViewAdapter = RVAdapter()
            adapter = myrecycleViewAdapter
        }
    }


    fun createData(){

        val item = ArrayList<Phones>()

        item.add(Phones("Moh0",1212, 1313, true))
        item.add(Phones("Moh01",12121, 13131, true))
        item.add(Phones("Moh02",12122, 13132, true))
        item.add(Phones("Moh03",12123, 13133, true))
        item.add(Phones("Moh04",12124, 13134, true))
        item.add(Phones("Moh05",12125, 13135, true))
        item.add(Phones("Moh06",12126, 13136, true))
        item.add(Phones("Moh07",12127, 13137, true))
        item.add(Phones("Moh08",12128, 13138, true))
        item.add(Phones("Moh09",12129, 13139, true))
        item.add(Phones("Moh010",121210, 131310, true))

        myrecycleViewAdapter.setListData(item)
        myrecycleViewAdapter.notifyDataSetChanged()

    }

}