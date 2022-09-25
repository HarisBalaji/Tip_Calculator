package com.example.tipcalculator

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var bind : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.calculateButton.setOnClickListener{ calculateTip() }

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("blue")))
    }

    private fun calculateTip(){
        val stringInTextField = bind.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
        val checkbutton =bind.tipOptions.checkedRadioButtonId
        val tipper = when (checkbutton) {
            R.id.option_eighteen_percent -> 0.18
            R.id.option_twenty_percent -> 0.2
            else -> 0.15
        }
        var tip = cost * tipper
        val roundUp=bind.roundupSwitch.isChecked
        if(roundUp){
            tip= ceil(tip)
        }
        val formatTip = NumberFormat.getCurrencyInstance(Locale.US).format(tip)
        bind.tipResult.text=getString(R.string.tipAmount, formatTip)
    }

}