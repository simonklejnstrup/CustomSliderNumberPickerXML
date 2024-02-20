package com.example.customsliderassignment

import android.view.MotionEvent
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.view.ViewGroup


class TouchListener(private var slidingView: View) : View.OnTouchListener {

    private var initHeight = 0
    private var initPos = 0f
    private var params: ViewGroup.LayoutParams? = null

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (params == null) {
            params = slidingView.layoutParams
        }

        when (event!!.actionMasked) {
            ACTION_DOWN -> {
                initHeight = slidingView.height
                initPos = event.rawY
            }

            ACTION_MOVE -> {
                val dPos = initPos - event.rawY
                params!!.height = Math.round(initHeight + dPos)
                slidingView.requestLayout() //refresh layout
            }
        }

        return false
    }

}