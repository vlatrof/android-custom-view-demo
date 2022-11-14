package com.vlatrof.customviewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // Paint object for coloring
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Some preinstalled styling values
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    private var borderWidth = 4.0f

    // View size in pixels
    private var size = 320

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}
