package com.material.service.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.material.service.calculator.databinding.ActivityItemManagerBinding

class ItemManager : AppCompatActivity() {

    private var _binding:ActivityItemManagerBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityItemManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}