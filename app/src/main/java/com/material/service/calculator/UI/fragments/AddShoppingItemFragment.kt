package com.material.service.calculator.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.material.service.calculator.R
import com.material.service.calculator.RoomDB.ShoppingItem
import com.material.service.calculator.databinding.FragmentAddShoppingItemBinding
import com.material.service.calculator.viewmodel.ShoppingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    private var _binding:FragmentAddShoppingItemBinding?=null
    private val binding get() = _binding!!
    private val viewModel: ShoppingViewModel by lazy {
        ViewModelProvider(this).get(ShoppingViewModel::class.java)
    }

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
        GlobalScope.launch(Dispatchers.IO) {
        viewModel.insertShoppingItemIntoDb(ShoppingItem(itemName,itemAmount,itemPrice,imageUrl))
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}