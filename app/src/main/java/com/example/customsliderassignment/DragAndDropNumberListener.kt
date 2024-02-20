package com.example.customsliderassignment


import android.content.ClipDescription
import android.view.DragEvent
import android.view.View
import androidx.core.content.ContextCompat

class DragAndDropNumberListener : View.OnDragListener {
    override fun onDrag(view: View, event: DragEvent): Boolean {
        return when (event.action) {
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.setNumberAsInActive(); true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                view.setNumberAsInActive(); true
            }
            DragEvent.ACTION_DROP -> {
                val draggingView = event.localState as View
                val draggingViewParent = draggingView.parent as DragAndDropNumberContainer
                draggingViewParent.removeContent(draggingView)

                val landingContainer = view as DragAndDropNumberContainer
                landingContainer.setContent(draggingView)
                landingContainer.setNumberAsActive()
                true
            }
            DragEvent.ACTION_DRAG_STARTED -> event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            else -> true
        }
    }


    private fun View.setNumberAsActive() {
        background = ContextCompat.getDrawable(context, R.drawable.active_number_background)
    }
    private fun View.setNumberAsInActive() {
        background = null
    }

}