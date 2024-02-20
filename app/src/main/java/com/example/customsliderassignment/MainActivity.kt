package com.example.customsliderassignment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val dragView = findViewById<View>(R.id.drag_view)
//        dragView.setOnClickListener {
//            val dragShadowBuilder = View.DragShadowBuilder(it)
//            it.startDragAndDrop(null, dragShadowBuilder, it, 0)
//
//            it.visibility = View.INVISIBLE
//            true
//        }
//        val view1 = findViewById<LinearLayout>(R.id.ll_1)
//        val view0 = findViewById<LinearLayout>(R.id.ll_0)
//        view1.setOnDragListener(dragListener)
//        view0.setOnDragListener(dragListener)

        val llRootLayout = findViewById<LinearLayout>(R.id.root_layout)
        val draggableView = findViewById<View>(R.id.draggable_view)
        draggableView.setOnTouchListener(TouchListener(llRootLayout))
        draggableView.isClickable = true


    }

    val dragListener = View.OnDragListener { view, event ->
        when(event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                view.invalidate()
                val v = event.localState as View
                val owner = v.parent as ViewGroup
                owner.removeView(v)
                val destination = view as LinearLayout
                destination.addView(v)
                v.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> false
        }
    }
}