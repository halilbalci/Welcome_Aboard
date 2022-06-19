package com.example.welcomeaboard

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class TipCalculatorTest {
    @Test
    fun calculateTipTest(){
        val tipC = TipCalculator()
        assertEquals(tipC.calculateTip("100",0.20,false),20.0,0.1)
        assertEquals(tipC.calculateTip("115",0.20,true),23.0,0.1)
        assertEquals(tipC.calculateTip("100",0.18,false),18.0,0.1)
        assertEquals(tipC.calculateTip("115",0.18,true),21.0,0.1)
        assertEquals(tipC.calculateTip("100",0.15,false),15.0,0.1)
        assertEquals(tipC.calculateTip("115",0.15,true),18.0,0.1)

    }
}