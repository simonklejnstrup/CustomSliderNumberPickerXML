package com.example.customsliderassignment

import android.annotation.SuppressLint
import android.graphics.Rect
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val llRootLayout = findViewById<LinearLayout>(R.id.root_layout)
        val draggableView = findViewById<View>(R.id.draggable_view)
        val tv1 = findViewById<TextView>(R.id.tv_text_1)
        val tv2 = findViewById<TextView>(R.id.tv_text_2)
        val tv3 = findViewById<TextView>(R.id.tv_text_3)
        val tv4 = findViewById<TextView>(R.id.tv_text_4)
        val tv5 = findViewById<TextView>(R.id.tv_text_5)
        val tv6 = findViewById<TextView>(R.id.tv_text_6)
        val tv7 = findViewById<TextView>(R.id.tv_text_7)
        val tv8 = findViewById<TextView>(R.id.tv_text_8)

        val viewArray = arrayOf(tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8)


        var rightDX: Float = 0f
        var rightDY: Float
        draggableView.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {

                    rightDX = view!!.x - event.rawX
                    rightDY = view!!.getY() - event.rawY;

                }
                MotionEvent.ACTION_MOVE -> {

                    var displacement = event.rawX + rightDX

                    view!!.animate()
                        .x(displacement)
                        // .y(event.getRawY() + rightDY)
                        .setDuration(0)
                        .start()

                    val boldTypeface = Typeface.create("open_sans_bold", Typeface.BOLD)
                    val defaultTypeface = Typeface.create("open_sans_regular", Typeface.NORMAL)
                    viewArray.forEach { textView ->
                        when {
                            viewsOverlap(view, textView) -> {
                                textView.typeface = boldTypeface
                                textView.setTextColor(ContextCompat.getColor(this, R.color.active_number))
                            }
                            else -> {
                                // Reset to default properties if there's no overlap
                                textView.typeface = defaultTypeface
                                textView.setTextColor(ContextCompat.getColor(this, R.color.inactive_number))
                            }
                        }
                    }
                }
                else -> { // Note the block
                    return@OnTouchListener false
                }
            }
            true
        })
//        draggableView.isClickable = true
    }

    private fun viewsOverlap(v1: View, v2: View): Boolean {
        val v1Coords = IntArray(2)
        v1.getLocationOnScreen(v1Coords)
        val v1W = v1.width / 2 // Modify width to be half
        val v1H = v1.height
        val v1Rect = Rect(v1Coords[0], v1Coords[1], v1Coords[0] + v1W, v1Coords[1] + v1H)

        val v2Coords = IntArray(2)
        v2.getLocationOnScreen(v2Coords)
        val v2W = v2.width / 2 // Modify width to be half
        val v2H = v2.height
        val v2Rect = Rect(v2Coords[0], v2Coords[1], v2Coords[0] + v2W, v2Coords[1] + v2H)

        return v1Rect.intersect(v2Rect) || v1Rect.contains(v2Rect) || v2Rect.contains(v1Rect)
    }


}