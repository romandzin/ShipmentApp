package com.example.shipmentapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.shipmentapp.adapters.DeliveryAdapter
import com.example.shipmentapp.data.Delivery
import com.example.shipmentapp.viewModel.MainViewModel
import kotlinx.coroutines.launch

class DeliveryFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var deliveryRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_delivery, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        deliveryRecyclerView = view.findViewById(R.id.deliveryRecyclerView)
        viewModel.deliveryList.observe(viewLifecycleOwner) {deliveryList ->
            deliveryRecyclerView.adapter = DeliveryAdapter(deliveryList as ArrayList<Delivery>, viewModel, this)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getDeliveryList()
        }
    }
}