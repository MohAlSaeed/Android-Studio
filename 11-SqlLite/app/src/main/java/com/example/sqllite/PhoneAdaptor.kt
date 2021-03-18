package com.example.sqllite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sqllite.databinding.ActivityMainBinding
import com.example.sqllite.databinding.ListElementBinding

class PhoneAdaptor(val context: Context, private val phoneViewModel: PhoneViewModel) : RecyclerView.Adapter< PhoneAdaptor.PhoneViewHolder>() {

    private var  listOfPhones = emptyList<Phone>()



    class PhoneViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(inflater)
        return PhoneViewHolder(binding)
    }

    override fun onBindViewHolder(holderPhone: PhoneViewHolder, position: Int) {
        val currentPhone: Phone = listOfPhones[position]
        holderPhone.binding.rvtv1.text = currentPhone.id.toString()
        holderPhone.binding.rvtv2.text = currentPhone.name
        holderPhone.binding.rvtv3.text = currentPhone.price.toString()
        holderPhone.binding.rvtv4.text = currentPhone.quantity.toString()
        holderPhone.binding.rvcb.isChecked = currentPhone.available
        holderPhone.binding.rvbt1.setOnClickListener {
            phoneViewModel.delete(currentPhone)
            Toast.makeText(
                context,
                "Phone Stock with id: ${currentPhone.id} and name: ${currentPhone.name} is removed. ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = listOfPhones.size
    fun setPhones(phones : List<Phone>){
        listOfPhones = phones
        notifyDataSetChanged()

    }
}
