package com.example.shipmentapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.shipmentapp.data.Delivery
import com.example.shipmentapp.viewModel.MainViewModel

class CustomDialogClass(private val fragment: Fragment, private val viewModel: MainViewModel, val delivery: Delivery): Dialog(fragment.requireContext()) {

    init {
        setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.complete_dialog_layout)
        initView()
    }

    private fun initView() {
        val fioEditText = findViewById<EditText>(R.id.fioField)
        val timeEditText = findViewById<EditText>(R.id.timeField)
        val completeButton = findViewById<Button>(R.id.completeButton)
        completeButton.setOnClickListener {
            viewModel.successfulResponse.observe(fragment) {
                viewModel.addItemToCompleteList(delivery)
                dismiss()
                viewModel.successfulResponse.removeObservers(fragment)
            }
            viewModel.sendCompleteData(fioEditText.text.toString(), timeEditText.text.toString())
        }
    }
}