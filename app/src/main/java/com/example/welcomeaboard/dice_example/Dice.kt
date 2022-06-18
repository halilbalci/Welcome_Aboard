package com.example.welcomeaboard.dice_example

import androidx.annotation.DrawableRes
import com.example.welcomeaboard.R

class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }

    fun dicePics(num:Int): Int {
        return when (num) {
            1 ->  R.drawable.dice_1
            2 ->  R.drawable.dice_2
            3 ->  R.drawable.dice_3
            4 ->  R.drawable.dice_4
            5 ->  R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}