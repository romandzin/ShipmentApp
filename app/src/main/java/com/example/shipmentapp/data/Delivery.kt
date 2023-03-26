package com.example.shipmentapp.data

import com.google.gson.annotations.SerializedName

data class Delivery(
    @SerializedName("id") val id: Int,
    @SerializedName("plan_time") val plan_time: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("lon") val lon: String,
    @SerializedName("address") val address: String,
    @SerializedName("phone") val phone: String,
)
