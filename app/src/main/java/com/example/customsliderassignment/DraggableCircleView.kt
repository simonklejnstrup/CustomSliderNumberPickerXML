package com.example.customsliderassignment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

class DraggableCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_draggable_circle, this, true)

        // Set up touch listener
        setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Handle touch down event
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    // Handle touch move event
                    val newX = event.x - (view.width / 2)
                    val newY = view.y

                    val parentWidth = (view.parent as View).width.toFloat()
                    if (newX >= 0 && newX + view.width <= parentWidth) {
                        view.x = newX
                        view.y = newY
                    }

                    true
                }
                else -> false
            }
        }
    }
}
