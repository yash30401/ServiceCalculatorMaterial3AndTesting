package com.material.service.calculator.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.material.service.calculator.R
import com.material.service.calculator.UI.fragments.AddShoppingItemFragment
import com.material.service.calculator.databinding.ActivityItemManagerBinding

class ItemManager : AppCompatActivity() {

    private var _binding:ActivityItemManagerBinding? =null
    private val binding get() = _binding!!

    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityItemManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager = supportFragmentManager
        binding.btnAddItem.setOnClickListener {
            openDialogFragment()
        }
    }



    private fun openDialogFragment() {
        fragmentManager.beginTransaction().add(R.id.item_managerLayout,AddShoppingItemFragment()).addToBackStack("item_manager").commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}