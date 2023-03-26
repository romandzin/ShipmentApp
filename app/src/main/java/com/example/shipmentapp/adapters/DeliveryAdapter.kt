package com.example.shipmentapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.shipmentapp.CustomDialogClass
import com.example.shipmentapp.R
import com.example.shipmentapp.data.Delivery
import com.example.shipmentapp.viewModel.MainViewModel

class DeliveryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val id = itemView.findViewById<TextView>(R.id.id)
    var plan_time = itemView.findViewById<TextView>(R.id.plan_time)
    var lat = itemView.findViewById<TextView>(R.id.lat)
    var lon = itemView.findViewById<TextView>(R.id.lon)
    var address = itemView.findViewById<TextView>(R.id.address)
    var phone = itemView.findViewById<TextView>(R.id.phone)
}

class DeliveryAdapter(private val deliveryArrayList: ArrayList<Delivery>, val viewModel: MainViewModel, val fragment: Fragment):
    RecyclerView.Adapter<DeliveryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.delivery_item, parent, false)
        val myViewHolder = DeliveryViewHolder(itemView)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.id.text = deliveryArrayList[position].id.toString()
        holder.plan_time.text = deliveryArrayList[position].plan_time
        holder.lat.text = deliveryArrayList[position].lat
        holder.lon.text = deliveryArrayList[position].lon
        holder.address.text = deliveryArrayList[position].address
        holder.phone.text = deliveryArrayList[position].phone
        holder.itemView.setOnClickListener {
            CustomDialogClass(fragment, viewModel, deliveryArrayList[position]).show()
            notifyDataSetChanged()
            notifyItemRangeChanged(0, deliveryArrayList.size)
        }
    }

    override fun getItemCount(): Int {
        return deliveryArrayList.size
    }

}