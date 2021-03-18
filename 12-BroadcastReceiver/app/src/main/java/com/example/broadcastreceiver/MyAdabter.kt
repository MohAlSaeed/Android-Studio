package com.example.broadcastreceiver

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.broadcastreceiver.databinding.ActivityStockBinding
import com.example.broadcastreceiver.databinding.ListElementBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyAdabter(val context: Context ,val phoneViewModel: PhoneViewModel ) : RecyclerView.Adapter<MyAdabter.ViewHolder>() {

    private var listOFPhones = emptyList<Phone>()

    class ViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)  {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentphone: Phone = listOFPhones[position]
        holder.binding.rvtv1.text = currentphone.name.toString()
        holder.binding.rvtv2.text = currentphone.price.toString()
        holder.binding.rvtv3.text = currentphone.quantity.toString()
        holder.binding.rvcb.isChecked = currentphone.available
        holder.binding.rvbt.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                phoneViewModel.delete(currentphone)
            }

            Toast.makeText(context,
                "Phone: ${currentphone.name} price: ${currentphone.price} quantity: ${currentphone.quantity} is removed",
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.binding.rvcb.setOnCheckedChangeListener(){
            _, isChecked -> if (currentphone.available == true){
            currentphone.available = false
            CoroutineScope(Dispatchers.IO).launch {
                phoneViewModel.update(currentphone)
            }

        } else {
            currentphone.available = true
            CoroutineScope(Dispatchers.IO).launch {
                phoneViewModel.update(currentphone)
            }

        }
            Toast.makeText(context,
                    "Phone: ${currentphone.name} price: ${currentphone.price} quantity: ${currentphone.quantity} updated availability to ${currentphone.available}",
                    Toast.LENGTH_SHORT
            ).show()
        }




    }

    override fun getItemCount(): Int = listOFPhones.size


        fun setPhones(phones: List<Phone>){
            listOFPhones = phones
            notifyDataSetChanged()
        }


}
