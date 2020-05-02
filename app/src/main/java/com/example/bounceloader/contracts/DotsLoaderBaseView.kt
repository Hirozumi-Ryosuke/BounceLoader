package com.example.bounceloader.contracts

import android.R.color.*
import android.content.Context
import android.graphics.Paint
import android.graphics.Paint.Style.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat.getColor
import com.example.bounceloader.R.color.*
import com.example.bounceloader.R.styleable.*
import com.example.bounceloader.utils.Helper.adjustAlpha


abstract class DotsLoaderBaseView : View, LoaderContract {

    var animDur = 500

    lateinit var dotsXCorArr: FloatArray

    protected var defaultCirclePaint: Paint? = null
    protected var selectedCirclePaint: Paint? = null

    protected lateinit var firstShadowPaint: Paint
    protected lateinit var secondShadowPaint: Paint

    private var isShadowColorSet = false

    protected var shouldAnimate = true

    protected var selectedDotPos = 1

    protected var logTime: Long = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun initAttributes(attrs: AttributeSet) {

        val typedArray = context.obtainStyledAttributes(attrs, DotsLoaderBaseView, 0, 0)

        defaultColor = typedArray.getColor(DotsLoaderBaseView_loader_defaultColor, getColor(context, loader_defalut))
        selectedColor = typedArray.getColor(DotsLoaderBaseView_loader_selectedColor, getColor(context, loader_defalut))

        radius = typedArray.getDimensionPixelSize(DotsLoaderBaseView_loader_circleRadius, 30)

        animDur = typedArray.getInt(DotsLoaderBaseView_loader_animDur, 500)

        showRunningShadow = typedArray.getBoolean(DotsLoaderBaseView_loader_showRunningShadow, true)

        firstShadowColor = typedArray.getColor(DotsLoaderBaseView_loader_firstShadowColor, 0)
        secondShadowColor = typedArray.getColor(DotsLoaderBaseView_loader_secondShadowColor, 0)

        typedArray.recycle()
    }

    protected abstract fun initCordinates()

    //init paints for drawing dots
    fun initPaints() {
        defaultCirclePaint = Paint()
        defaultCirclePaint?.isAntiAlias = true
        defaultCirclePaint?.style = FILL
        defaultCirclePaint?.color = defaultColor

        selectedCirclePaint = Paint()
        selectedCirclePaint?.isAntiAlias = true
        selectedCirclePaint?.style = FILL
        selectedCirclePaint?.color = selectedColor
    }

    //init paints for drawing shadow dots
    fun initShadowPaints() {
        if (showRunningShadow) {
            if (!isShadowColorSet) {
                firstShadowColor = adjustAlpha(selectedColor, 0.7f)
                secondShadowColor = adjustAlpha(selectedColor, 0.5f)
                isShadowColorSet = true
            }

            firstShadowPaint = Paint()
            firstShadowPaint.isAntiAlias = true
            firstShadowPaint.style = FILL
            firstShadowPaint.color = firstShadowColor

            secondShadowPaint = Paint()
            secondShadowPaint.isAntiAlias = true
            secondShadowPaint.style = FILL
            secondShadowPaint.color = secondShadowColor
        }
    }

    fun startAnimation() {
        shouldAnimate = true
        invalidate()
    }

    fun stopAnimation() {
        shouldAnimate = false
        invalidate()
    }

    var defaultColor = getColor(context, darker_gray)
        set(defaultColor) {
            field = defaultColor
            defaultCirclePaint?.color = defaultColor
        }

    open var selectedColor = getColor(context, loader_defalut)
        set(selectedColor) {
            field = selectedColor
            selectedCirclePaint?.let {
                it.color = selectedColor
                initShadowPaints()
            }
        }

    var radius = 30
        set(radius) {
            field = radius
            initCordinates()
        }

    var showRunningShadow = true

    var firstShadowColor = 0
        set(value) {
            field = value
            if (value != 0) {
                isShadowColorSet = true
                initShadowPaints()
            }
        }


    var secondShadowColor = 0
        set(value) {
            field = value
            if (value != 0) {
                isShadowColorSet = true
                initShadowPaints()
            }
        }
}