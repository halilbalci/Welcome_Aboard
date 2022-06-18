package com.example.welcomeaboard

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.welcomeaboard.dice_example.Dice

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.welcomeaboard", appContext.packageName)
    }
    @Test
    fun isDiceRolling(){
        val dice = Dice(6)
        val rollResult = dice.roll()
        assertTrue("The value of rollResult was not between 1 and 6", rollResult in 1..6)
    }
}