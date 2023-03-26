package com.example.shipmentapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shipmentapp.data.Delivery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application): AndroidViewModel(application) {

    val deliveryList = MutableLiveData<MutableList<Delivery>>()
    val completedList = MutableLiveData<MutableList<Delivery>>()
    val successfulResponse = MutableLiveData<Boolean>()

    val model = com.example.shipmentapp.data.Model()

    fun getDeliveryList() {
        if (deliveryList.value == null) {
            viewModelScope.launch {
                val deliveriesList = withContext(Dispatchers.IO) {
                    model.getDeliveryList().execute().body()
                }
                deliveryList.value = deliveriesList as MutableList<Delivery>?
            }
        }
    }

    fun createCompletedList() {
        completedList.value = arrayListOf()
    }

    fun sendCompleteData(fio: String, time: String) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                model.sendCompleteData(fio, time).execute()
            }
            successfulResponse.value = response.isSuccessful
        }
    }

    fun addItemToCompleteList(completedDelivery: Delivery) {
        deliveryList.value?.remove(completedDelivery)
        deliveryList.notifyObserver()
        completedList.value?.add(completedDelivery)
        completedList.notifyObserver()
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}