package com.example.bounceloader.contracts

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.*
import com.example.bounceloader.R.color.*

abstract class AbstractLinearLayout : LinearLayout, LoaderContract {

    open var animDuration = 500

    open var interpolator = AnticipateOvershootInterpolator()

    var dotsRadius = 30

    var dotsDist = 15

    var dotsColor = getColor(context, loader_defalut)

    abstract fun initView()

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

}