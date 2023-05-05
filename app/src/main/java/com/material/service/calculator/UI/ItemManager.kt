package com.material.service.calculator.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.material.service.calculator.R
import com.material.service.calculator.UI.fragments.AddShoppingItemFragment
import com.material.service.calculator.adapter.ItemAdapter
import com.material.service.calculator.databinding.ActivityItemManagerBinding
import com.material.service.calculator.viewmodel.ShoppingViewModel
import java.text.NumberFormat

class ItemManager : AppCompatActivity() {

    private var _binding:ActivityItemManagerBinding? =null
    private val binding get() = _binding!!
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var viewModel : ShoppingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityItemManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ShoppingViewModel::class.java)

        //Setting Up Recylerview
        setupRecylerView()

        binding.btnAddItem.setOnClickListener {
            openDialogFragment()
        }

        //Observing Items
        viewModel.shoppingItems.observe(this, Observer {
            itemAdapter.submitList(it)
        })

        //Observing Total Price
        viewModel.totalPrice.observe(this, Observer {
            binding.tvTotalPrice.text = NumberFormat.getCurrencyInstance().format(it)
        })
    }
    private fun setupRecylerView() = binding.recylerView.apply {
        itemAdapter = ItemAdapter()
        adapter = itemAdapter
        layoutManager = LinearLayoutManager(this@ItemManager)
    }


    private fun openDialogFragment() {
        supportFragmentManager.beginTransaction().add(R.id.item_managerLayout,AddShoppingItemFragment()).addToBackStack("item_manager").commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}