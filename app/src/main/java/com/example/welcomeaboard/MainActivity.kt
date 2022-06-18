package com.example.welcomeaboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.welcomeaboard.dice_example.Dice

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.barbut_playground)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { playBarbut() }
    }
    fun playBarbut(){
        val diceImage: ImageView = findViewById(R.id.imageView2)
        val dice =  Dice(6)
        diceImage.setImageResource(dice.dicePics(dice.roll()))
    }
}