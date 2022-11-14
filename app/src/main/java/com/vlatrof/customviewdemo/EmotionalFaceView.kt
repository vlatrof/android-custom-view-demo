package com.vlatrof.customviewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class EmotionalFaceView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var size = 0
    private val mouthPath = Path()
    private val paint = Paint()

    private var faceColor = FACE_COLOR_DEFAULT
    private var eyesColor = EYES_COLOR_DEFAULT
    private var mouthColor = MOUTH_COLOR_DEFAULT
    private var borderColor = BORDER_COLOR_DEFAULT
    private var borderWidth = BORDER_WIDTH_DEFAULT

    private var happinessState = HAPPINESS_STATE_DEFAULT
        set(state) {
            field = state
            invalidate()
        }

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EmotionalFaceView,
            0,
            0
        )

        happinessState = typedArray.getInt(
            R.styleable.EmotionalFaceView_happinessState, HAPPINESS_STATE_DEFAULT.toInt()).toLong()
        faceColor = typedArray.getColor(
            R.styleable.EmotionalFaceView_faceColor, FACE_COLOR_DEFAULT)
        eyesColor = typedArray.getColor(
            R.styleable.EmotionalFaceView_eyesColor, EYES_COLOR_DEFAULT)
        mouthColor = typedArray.getColor(
            R.styleable.EmotionalFaceView_mouthColor, MOUTH_COLOR_DEFAULT)
        borderColor = typedArray.getColor(
            R.styleable.EmotionalFaceView_borderColor, BORDER_COLOR_DEFAULT)
        borderWidth = typedArray.getDimension(
            R.styleable.EmotionalFaceView_borderWidth, BORDER_WIDTH_DEFAULT)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        val radius = size / 2f

        // background fill
        paint.color = faceColor
        paint.style = Paint.Style.FILL
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        // background border
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        paint.color = eyesColor
        paint.style = Paint.Style.FILL

        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)
        canvas.drawOval(leftEyeRect, paint)

        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)
        canvas.drawOval(rightEyeRect, paint)
    }

    private fun drawMouth(canvas: Canvas) {
        mouthPath.reset()

        mouthPath.moveTo(size * 0.22f, size * 0.7f)

        when (happinessState) {
            HAPPINESS_STATE_HAPPY -> {
                mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f)
                mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f)
            }
            HAPPINESS_STATE_SAD -> {
                mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
                mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
            }
        }

        paint.color = mouthColor
        paint.style = Paint.Style.FILL
        canvas.drawPath(mouthPath, paint)
    }

    companion object {
        private const val FACE_COLOR_DEFAULT = Color.YELLOW
        private const val EYES_COLOR_DEFAULT = Color.BLACK
        private const val MOUTH_COLOR_DEFAULT = Color.BLACK
        private const val BORDER_COLOR_DEFAULT = Color.BLACK
        private const val BORDER_WIDTH_DEFAULT = 4.0f

        const val HAPPINESS_STATE_HAPPY = 0L
        const val HAPPINESS_STATE_SAD = 1L
        private const val HAPPINESS_STATE_DEFAULT = HAPPINESS_STATE_HAPPY
    }
}
