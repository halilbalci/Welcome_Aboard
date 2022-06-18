package com.example.welcomeaboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.welcomeaboard.databinding.ActivityMainBinding
import com.example.welcomeaboard.databinding.TipCalculatorBinding
import com.example.welcomeaboard.dice_example.Dice
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: TipCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TipCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener{calculateTip()}

        //setContentView(R.layout.barbut_playground)
        //val rollButton: Button = findViewById(R.id.button)
        //rollButton.setOnClickListener { playBarbut() }
    }
    private fun calculateTip(){
        val costString = binding.textInputTip.text.toString()
        val costDouble = costString.toDoubleOrNull()
        if (costDouble == null) {
            binding.tipResult.text = ""
            return
        }
        val tipPercent = when(binding.radioGroup.checkedRadioButtonId){
            R.id.percent20-> 0.20
            R.id.percent18-> 0.18
            else-> 0.15
        }
        var tip = tipPercent*costDouble
        if(binding.switchCompat.isChecked) tip = kotlin.math.ceil(tip)
        binding.tipResult.text = getString(R.string.tip_amount, NumberFormat.getCurrencyInstance().format(tip))
    }

    fun playBarbut(){
        val diceImage: ImageView = findViewById(R.id.imageView2)
        val diceImage2: ImageView = findViewById(R.id.imageView3)
        val dice =  Dice(6)
        diceImage.setImageResource(dice.dicePics(dice.roll()))
        diceImage2.setImageResource(dice.dicePics(dice.roll()))
    }
}