package com.material.service.calculator.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.material.service.calculator.R
import com.material.service.calculator.UI.fragments.AddShoppingItemFragment
import com.material.service.calculator.adapter.ItemAdapter
import com.material.service.calculator.databinding.ActivityItemManagerBinding
import com.material.service.calculator.viewmodel.ShoppingViewModel

class ItemManager : AppCompatActivity() {

    private var _binding:ActivityItemManagerBinding? =null
    private val binding get() = _binding!!
    private lateinit var itemAdapter: ItemAdapter
    private val viewModel : ShoppingViewModel by lazy {
        ViewModelProvider(this).get(ShoppingViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityItemManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecylerView()

        binding.btnAddItem.setOnClickListener {
            openDialogFragment()
        }

        viewModel.shoppingItems.observe(this, Observer {
            itemAdapter.submitList(it)
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