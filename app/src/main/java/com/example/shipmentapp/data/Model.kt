package com.example.shipmentapp.data

import retrofit2.Call

class Model {

    private val retrofitService = RetrofitService.retrofitService

    fun getDeliveryList(): Call<List<Delivery>> {
        return retrofitService.getDeliveryList()
    }

    fun sendCompleteData(fio: String, time: String): Call<Unit> {
        return retrofitService.sendData(fio, time)
    }
}