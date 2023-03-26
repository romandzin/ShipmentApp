package com.example.shipmentapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DeliveryApi {

    @GET("https://vseotlichno.com/api/v1/get_delivery.php")
    fun getDeliveryList(): Call<List<Delivery>>

    @POST("https://vseotlichno.com/api/v1/set_delivery.php")
    fun sendData(
        @Query("fio") fio: String,
        @Query("time") time: String,
    ): Call<Unit>
}