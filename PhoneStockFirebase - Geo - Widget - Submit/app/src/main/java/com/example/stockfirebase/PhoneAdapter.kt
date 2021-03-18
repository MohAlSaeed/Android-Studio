package com.example.stockfirebase

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.item_list.view.*

class PhoneAdapter(val phoneList: MutableList<Phone>) : RecyclerView.Adapter<PhoneAdapter.PhoneAdapterViewHolder>() {
//    val phoneList: MutableList<Phone>
    class PhoneAdapterViewHolder(item: View) : RecyclerView.ViewHolder(item){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val tv = inflater.inflate(R.layout.item_list,parent,false)

        return PhoneAdapterViewHolder(tv)
    }

    override fun onBindViewHolder(holder: PhoneAdapterViewHolder, position: Int) {
        holder.itemView.oneitemtv0.text = phoneList[position].id
        holder.itemView.oneitemtv1.text = phoneList[position].name
        holder.itemView.oneitemtv2.text = phoneList[position].price.toString()
        holder.itemView.oneitemtv3.text = phoneList[position].quantity.toString()
        holder.itemView.oneitemrb.isChecked = phoneList[position].available
        var currentphone : Phone = phoneList[position]



        // the modify part is here
        holder.itemView.oneitemcb.setOnCheckedChangeListener { _, isChecked ->
            if (currentphone.available == true){
                currentphone.available = false
                updatePhone(currentphone)
            } else {
                currentphone.available = true
                updatePhone(currentphone)
            }
         }

        // the delete phone part
        holder.itemView.oneitembt.setOnClickListener {
            deletePhone(currentphone)
        }

    }








    private fun deletePhone(currentphone: Phone) {
        val db = FirebaseDatabase.getInstance().getReference("PhonesCollection")
        db.child(currentphone.id).removeValue()
    }


    private fun updatePhone(currentphone: Phone) {
        val db = FirebaseDatabase.getInstance().getReference("PhonesCollection")
        db.child(currentphone.id).setValue(currentphone)
//            .addOnCompleteListener {
//            Toast.makeText(this, "Phone added", Toast.LENGTH_SHORT).show()
//        }.addOnFailureListener {
//            Toast.makeText(this, "Phone not added", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }
}
