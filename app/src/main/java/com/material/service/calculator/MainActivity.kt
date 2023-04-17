package com.material.service.calculator

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.color.DynamicColors
import com.material.service.calculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

        binding.costOfServiceEditText.setOnKeyListener { view, i, keyEvent ->
            handleKeyEvent(view,i)
        }

        binding.btnItemManager.setOnClickListener {
            val intent = Intent(this@MainActivity,ItemManager::class.java)
            startActivity(intent)
        }
    }

    //Calculating Tip Percentage
    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if(cost==null|| cost==0.0){
            showResult(0.0)
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            //Rounding Up using Funtion Math.ciel()
            tip = kotlin.math.ceil(tip)

        }

        showResult(tip)
    }

    //Showing Formatted Result and current sign using .getCurrentInstance()
    private fun showResult(tip:Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    //Hiding Soft Keyboard after Pressing enter
    private fun handleKeyEvent(view: View, i: Int):Boolean {
        if(i==KeyEvent.KEYCODE_ENTER){
            val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}