package com.example.welcomeaboard

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.welcomeaboard.affirmationsRecycleView.adapter.AffirmationItemAdapter
import com.example.welcomeaboard.affirmationsRecycleView.data.DataSource
import com.example.welcomeaboard.databinding.ActivityMainBinding
import com.example.welcomeaboard.databinding.AffirmationLayoutBinding
import com.example.welcomeaboard.databinding.TipCalculatorBinding
import com.example.welcomeaboard.dice_example.Dice
import java.text.NumberFormat
import kotlin.math.abs
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var binding: TipCalculatorBinding
    private lateinit var swipeHelper: ItemTouchHelper

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
        val displayMetrics: DisplayMetrics = resources.displayMetrics
        val height = (displayMetrics.heightPixels / displayMetrics.density).toInt().dp
        val width = (displayMetrics.widthPixels / displayMetrics.density).toInt().dp

        val deleteIcon = resources.getDrawable(R.drawable.ic_baseline_delete_outline_24, null)
        val archiveIcon = resources.getDrawable(R.drawable.ic_baseline_fast_forward_24, null)

        val deleteColor = resources.getColor(android.R.color.holo_red_light)
        val archiveColor = resources.getColor(android.R.color.holo_green_light)


        swipeHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            //more code here
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                println(direction)
            }

            override fun onChildDraw(
                canvas: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                //1. Background color based upon direction swiped
                when {
                    abs(dX) < width / 3 -> canvas.drawColor(Color.GRAY)
                    dX > width / 3 -> canvas.drawColor(deleteColor)
                    else -> canvas.drawColor(archiveColor)
                }

                //2. Printing the icons
                val textMargin = resources.getDimension(R.dimen.text_margin)
                    .roundToInt()
                deleteIcon.bounds = Rect(
                    textMargin,
                    viewHolder.itemView.top + textMargin + 8.dp,
                    textMargin + deleteIcon.intrinsicWidth,
                    viewHolder.itemView.top + deleteIcon.intrinsicHeight
                            + textMargin + 8.dp
                )
                archiveIcon.bounds = Rect(
                    width - textMargin - archiveIcon.intrinsicWidth,
                    viewHolder.itemView.top + textMargin + 8.dp,
                    width - textMargin,
                    viewHolder.itemView.top + archiveIcon.intrinsicHeight
                            + textMargin + 8.dp
                )

                //3. Drawing icon based upon direction swiped
                if (dX > 0) deleteIcon.draw(canvas) else archiveIcon.draw(canvas)

                super.onChildDraw(
                    canvas,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        })

        setContentView(R.layout.affirmation_layout)
        val myDataset = DataSource().loadAffirmations()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = AffirmationItemAdapter(this,myDataset)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)//this method is only suitable for fixed size list. improve performance
        swipeHelper.attachToRecyclerView(recyclerView)

    }

    private val Int.dp
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            toFloat(), resources.displayMetrics
        ).roundToInt()
}