package com.example.bounceloader.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style.*
import android.util.AttributeSet
import android.view.View
import com.example.bounceloader.R.styleable.*

class CircleView : View {

    private var circleRadius = 30
    private var strokeWidth = 0

    private var circleColor = 0
    private var drawOnlyStroke = false

    private var isAntiAlias = true

    private var xyCordinates = 0.0f
    private val paint = Paint()

    constructor(context: Context, circleRadius: Int, circleColor: Int, isAntiAlias: Boolean = true) : super(context) {
        this.circleRadius = circleRadius
        this.circleColor = circleColor
        this.isAntiAlias = isAntiAlias

        initValues()
    }

    constructor(context: Context, circleRadius: Int, circleColor: Int, drawOnlyStroke: Boolean, strokeWidth: Int) : super(context) {
        this.circleRadius = circleRadius
        this.circleColor = circleColor

        this.drawOnlyStroke = drawOnlyStroke
        this.strokeWidth = strokeWidth

        initValues()
    }

    constructor(context: Context) : super(context) {
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initValues()
    }


    private fun initAttributes(attrs: AttributeSet) {

        val typedArray = context.obtainStyledAttributes(attrs, CircleView, 0, 0)

        circleRadius = typedArray.getDimensionPixelSize(CircleView_circleRadius, 30)
        circleColor = typedArray.getColor(CircleView_circleColor, 0)

        drawOnlyStroke = typedArray.getBoolean(CircleView_circleDrawOnlystroke, false)

        if (drawOnlyStroke) strokeWidth = typedArray.getDimensionPixelSize(
            CircleView_circleStrokeWidth, 0)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthHeight = 2 * (circleRadius) + strokeWidth
        setMeasuredDimension(widthHeight, widthHeight)
    }

    private fun initValues() {
        paint.isAntiAlias = isAntiAlias

        when {
            drawOnlyStroke -> {
                paint.style = STROKE
                paint.strokeWidth = strokeWidth.toFloat()
            }
            else -> paint.style = FILL
        }
        paint.color = circleColor

        //adding half of strokeWidth because
        //the stroke will be half inside the drawing circle and half outside
        xyCordinates = (circleRadius + (strokeWidth / 2)).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(xyCordinates, xyCordinates, circleRadius.toFloat(), paint)
    }
}