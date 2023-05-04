package com.material.service.calculator.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.material.service.calculator.R
import com.material.service.calculator.RoomDB.ShoppingItem
import com.material.service.calculator.adapter.ItemAdapter
import com.material.service.calculator.databinding.FragmentAddShoppingItemBinding
import com.material.service.calculator.viewmodel.ShoppingViewModel


class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    private var _binding:FragmentAddShoppingItemBinding?=null
    private val binding get() = _binding!!
    private val viewModel: ShoppingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddShoppingItemBinding.bind(view)

        binding.btnAddItemToDB.setOnClickListener {
            addItemToDb()
        }

    }

    private fun addItemToDb() {
        val itemName = binding.etItemName.text.toString()
        val itemAmount = binding.etAmount.text.toString().toInt()
        val itemPrice=  binding.etAmountperItem.text.toString().toFloat()
        val imageUrl = binding.etItemImageUrl.text.toString()
        viewModel.insertShoppingItemIntoDb(ShoppingItem(itemName,itemAmount,itemPrice,imageUrl))
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}