package com.example.welcomeaboard

class TipCalculator {
    fun calculateTip(costString:String,tipPercent:Double,isRound:Boolean): Double {
        val costDouble = costString.toDoubleOrNull() ?: return 0.0
        val tip =tipPercent*costDouble
        if(isRound) return kotlin.math.ceil(tip)
        return tip
    }
}