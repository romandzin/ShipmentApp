package com.example.shipmentapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.shipmentapp.adapters.CompletedAdapter
import com.example.shipmentapp.data.Delivery
import com.example.shipmentapp.viewModel.MainViewModel
import kotlinx.coroutines.launch


class CompletedDeliveryFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_completed_delivery, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        val completedRecyclerView = view.findViewById<RecyclerView>(R.id.completedRecyclerView)
        viewModel.completedList.observe(viewLifecycleOwner) {
            completedRecyclerView.adapter = CompletedAdapter(it as ArrayList<Delivery>, resources)
        }
        viewModel.createCompletedList()
    }

}