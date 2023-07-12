package com.seentechs.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View

/**
 * Created by AQUIB RASHID SHAIKH on 10-07-2023.
 */
class CustomThumbDrawable(private val context: Context) : Drawable() {

    private val bounds: Rect = Rect()
    private val tooltipLayout: View

    init {
        val inflater = LayoutInflater.from(context)
        tooltipLayout = inflater.inflate(R.layout.demo, null)
    }

    override fun draw(canvas: Canvas) {
        // Draw the thumb drawable if needed
    }

    override fun getConstantState(): ConstantState {
        return object : ConstantState() {
            override fun newDrawable(): Drawable {
                return CustomThumbDrawable(context)
            }

            override fun getChangingConfigurations(): Int {
                return 0
            }
        }
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun setAlpha(alpha: Int) {
        // Not needed for the custom thumb
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // Not needed for the custom thumb
    }
}
