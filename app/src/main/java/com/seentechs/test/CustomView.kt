package com.seentechs.test

/**
 * Created by AQUIB RASHID SHAIKH on 12-07-2023.
 */
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout


class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_view_layout, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomView)
            val layoutResId = typedArray.getResourceId(R.styleable.CustomView_customLayout, 0)
            typedArray.recycle()

            if (layoutResId != 0) {
                inflater.inflate(layoutResId, findViewById(R.id.frameLayout), true)
            }
        }
    }
}
