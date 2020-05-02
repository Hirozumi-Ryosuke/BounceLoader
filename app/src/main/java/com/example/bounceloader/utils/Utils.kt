package com.example.bounceloader.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

object Utils {

    fun scanForActivity(context: Context?): Activity? {
        return when (context) {
            null -> null
            is Activity -> context
            is ContextWrapper -> scanForActivity(context.baseContext)
            else -> null
        }
    }
}