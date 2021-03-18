package com.example.phonestock

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.phonestock.databinding.ListElementBinding
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_stock.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhoneAdaptor(val context: Context, private val phoneViewModel: PhoneViewModel,val sp: SharedPreferences) : RecyclerView.Adapter< PhoneAdaptor.PhoneViewHolder>() {
//class PhoneAdaptor(val context: Context, val list: ArrayList<Phone>, val phone_ref : DatabaseReference, val sp: SharedPreferences) : RecyclerView.Adapter< PhoneAdaptor.PhoneViewHolder>() {

    private var  listOfPhones = emptyList<Phone>()
    private var id = 0

    class PhoneViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(inflater)   /// maybe here you have to add (inflater , parent, false)
        return PhoneViewHolder(binding)
    }

    override fun onBindViewHolder(holderPhone: PhoneViewHolder, position: Int) {
        val intentReceiver3 = Intent("com.example.phonestock.UPDATE_PHONE")
        intentReceiver3.component = ComponentName("com.example.phonestock","com.example.phonestock.UpdateReceiver")

        val currentPhone: Phone = listOfPhones[position]
        val siz = sp.getFloat("siz", 10F)
        val col = sp.getInt("col", Color.BLACK)
        val col2 = sp.getInt("col2", Color.WHITE)
        holderPhone.binding.rvtv1.text = currentPhone.id.toString()
        holderPhone.binding.rvtv1.textSize = siz
        holderPhone.binding.rvtv1.setTextColor(col)
        holderPhone.binding.rvtv2.text = currentPhone.name
        holderPhone.binding.rvtv2.textSize = siz
        holderPhone.binding.rvtv2.setTextColor(col)
        holderPhone.binding.rvtv3.text = currentPhone.price.toString()
        holderPhone.binding.rvtv3.textSize = siz
        holderPhone.binding.rvtv3.setTextColor(col)
        holderPhone.binding.rvtv4.text = currentPhone.quantity.toString()
        holderPhone.binding.rvtv4.textSize = siz
        holderPhone.binding.rvtv4.setTextColor(col)
        holderPhone.binding.rvcb.isChecked = currentPhone.available
        holderPhone.binding.rvbt1.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
//                phoneViewModel.delete(currentPhone)
            }
            Toast.makeText(
                context,
                "Phone Stock with id: ${currentPhone.id} and name: ${currentPhone.name} is removed. ",
                Toast.LENGTH_SHORT
            ).show()
        }

        holderPhone.binding.rvsw1.setOnCheckedChangeListener(){
                _, isChecked -> if (currentPhone.available == true){
            currentPhone.available = false
            CoroutineScope(Dispatchers.IO).launch {
                
//                phoneViewModel.update(currentPhone)

            }

        } else {
            currentPhone.available = true
            CoroutineScope(Dispatchers.IO).launch {
//                phoneViewModel.update(currentPhone)
            }
        }
            intentReceiver3.putExtra("phone_update",currentPhone.name)
            intentReceiver3.putExtra("id",id++)
            context.sendBroadcast(intentReceiver3)
//            Toast.makeText(context,
//                "Phone: ${currentPhone.name} price: ${currentPhone.price} quantity: ${currentPhone.quantity} updated availability to ${currentPhone.available}",
//                Toast.LENGTH_SHORT
//            ).show()
        }
    }

    override fun getItemCount(): Int = listOfPhones.size
    fun setPhones(phones : List<Phone>){
        listOfPhones = phones
        notifyDataSetChanged()

    }
}