package com.example.shipmentapp.adapters

import android.content.res.Resources
import android.net.wifi.aware.AwareResources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shipmentapp.R
import com.example.shipmentapp.data.Delivery

class CompletedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val id = itemView.findViewById<TextView>(R.id.id)
    var plan_time = itemView.findViewById<TextView>(R.id.plan_time)
    var lat = itemView.findViewById<TextView>(R.id.lat)
    var lon = itemView.findViewById<TextView>(R.id.lon)
    var address = itemView.findViewById<TextView>(R.id.address)
    var phone = itemView.findViewById<TextView>(R.id.phone)
}

class CompletedAdapter(private val deliveryArrayList: ArrayList<Delivery>, val resources: Resources):
    RecyclerView.Adapter<CompletedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.delivery_item, parent, false)
        return CompletedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CompletedViewHolder, position: Int) {
        holder.id.text = deliveryArrayList[position].id.toString()
        holder.plan_time.text = deliveryArrayList[position].plan_time
        holder.lat.text = deliveryArrayList[position].lat
        holder.lon.text = deliveryArrayList[position].lon
        holder.address.text = deliveryArrayList[position].address
        holder.phone.text = deliveryArrayList[position].phone
        holder.itemView.background = resources.getDrawable(R.drawable.completed_background)
    }

    override fun getItemCount(): Int {
        return deliveryArrayList.size
    }

}