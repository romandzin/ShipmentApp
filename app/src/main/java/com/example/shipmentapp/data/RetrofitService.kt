package com.example.shipmentapp.data

object RetrofitService {
    val retrofitService: DeliveryApi get() = RetrofitClient.buildService(DeliveryApi::class.java)
}