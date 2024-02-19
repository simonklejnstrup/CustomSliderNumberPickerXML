package com.example.customsliderassignment

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

class NumberSwitcher
//@JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//)
    : LinearLayout
//    (
//    context, attrs, defStyleAttr
//)
{

    private lateinit var rlRootLayout: LinearLayout
    private lateinit var tvText: TextView


    constructor(context: Context) : super(context) {
        initNumberSwitcher(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initNumberSwitcher(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initNumberSwitcher(context)
    }

    private fun initNumberSwitcher(context: Context) {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        LayoutInflater.from(context)
            .inflate(R.layout.number_switcher_with_vertical_animation, this, true)
        rlRootLayout = findViewById(R.id.root_layout)
        tvText = findViewById(R.id.tv_text_1)
//        minimumHeight = resources.getDimension(R.dimen.slider_height).toInt()
//        setMeasuredDimension(
//            measuredWidth,
//            resources.getDimension(R.dimen.slider_height).toInt()
//        )
        // set root layout 2x height of bar + some extra to make room for animation
//        rlRootLayout.layoutParams.height =
//            (resources.getDimension(R.dimen.slider_height).toInt() * 2) + 4

        // set padding of root layout
//        rlRootLayout.setPadding(
//            // left
//            resources.getDimension(R.dimen.margin_20).toInt(),
//            0,
//            // right
//            resources.getDimension(R.dimen.margin_20).toInt(),
//            0
//        )

        refreshDrawableState()

    }


    private val paint: Paint = Paint()
    private var currentValue = 1
    private val numberOfValues = 8
    private val radius = 50f
    private val circleSpacing = 100f
    private val textColor = Color.BLACK
    private val textSize = 50f

//    init {
//        paint.color = Color.BLUE
//        paint.isAntiAlias = true
//        paint.textSize = textSize
//        paint.textAlign = Paint.Align.CENTER
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in 1..numberOfValues) {
            val x = (i - 1) * circleSpacing + circleSpacing / 2
            val y = height / 2f
            canvas.drawCircle(x, y, radius, paint)
            canvas.drawText(i.toString(), x, y + textSize / 4, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val clickedValue = ((event.x + circleSpacing / 2) / circleSpacing).toInt()
                if (clickedValue in 1..numberOfValues) {
                    currentValue = clickedValue
                    invalidate()
                    // Notify listener or perform any other action on value change
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}
