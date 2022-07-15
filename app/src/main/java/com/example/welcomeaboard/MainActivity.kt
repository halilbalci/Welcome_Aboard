package com.example.welcomeaboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.welcomeaboard.affirmationsRecycleView.adapter.AffirmationItemAdapter
import com.example.welcomeaboard.affirmationsRecycleView.data.DataSource
import com.example.welcomeaboard.databinding.ActivityMainBinding
import com.example.welcomeaboard.databinding.AffirmationLayoutBinding
import com.example.welcomeaboard.databinding.TipCalculatorBinding
import com.example.welcomeaboard.dice_example.Dice
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: TipCalculatorBinding
    lateinit var bindingAffirmation: AffirmationLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerViewRun()
    }

    private fun runCalculateTip(){
        binding = TipCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener{calcuteOnClick()}
    }

    private fun calcuteOnClick(){
        val tc = TipCalculator()
        val tip = tc.calculateTip(binding.costOfServiceEditText.text.toString(),getTipPercent(),binding.switchCompat.isChecked)
        binding.tipResult.text = getString(R.string.tip_amount, NumberFormat.getCurrencyInstance().format(tip))
    }

    private fun getTipPercent(): Double {
        return when(binding.radioGroup.checkedRadioButtonId){
            R.id.percent20-> 0.20
            R.id.percent18-> 0.18
            else-> 0.15
        }
    }

    fun runBarbut(){
        setContentView(R.layout.barbut_playground)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { playBarbut() }
    }
    fun playBarbut(){
        val diceImage: ImageView = findViewById(R.id.imageView2)
        val diceImage2: ImageView = findViewById(R.id.imageView3)
        val dice =  Dice(6)
        diceImage.setImageResource(dice.dicePics(dice.roll()))
        diceImage2.setImageResource(dice.dicePics(dice.roll()))
    }

    fun recyclerViewRun(){
        setContentView(R.layout.affirmation_layout)
        val myDataset = DataSource().loadAffirmations()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = AffirmationItemAdapter(this,myDataset)
        recyclerView.layoutManager = GridLayoutManager(baseContext,2)
        recyclerView.setHasFixedSize(true)//this method is only suitable for fixed size list. improve performance
    }

}