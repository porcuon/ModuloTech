package com.porcuon.modulotech.presentation.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.material.slider.LabelFormatter.LABEL_GONE
import com.google.android.material.slider.Slider

private const val DEFAULT_HALO_RADIUS_DP = 16F
private const val DEFAULT_THUMB_RADIUS_DP = 8F

class VerticalSlider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val slider = Slider(context, attrs, defStyleAttr).apply {
        labelBehavior = LABEL_GONE
        haloRadius = dpToPx(DEFAULT_HALO_RADIUS_DP, resources).toInt()
        thumbRadius = dpToPx(DEFAULT_THUMB_RADIUS_DP, resources).toInt()
    }

    var valueFrom: Float
        get() = slider.valueFrom
        set(valueFrom) {
            slider.valueFrom = valueFrom
        }

    var valueTo: Float
        get() = slider.valueTo
        set(valueTo) {
            slider.valueTo = valueTo
        }

    var value: Float
        get() = slider.value
        set(value) {
            slider.value = value
        }

    init {
        addView(slider)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val childView = getChildAt(0)

        if (childView != null && childView.visibility != GONE) {
            measureChild(childView, heightMeasureSpec, widthMeasureSpec)
            setMeasuredDimension(childView.measuredHeightAndState, childView.measuredWidthAndState)
        } else {
            setMeasuredDimension(
                resolveSizeAndState(0, widthMeasureSpec, 0),
                resolveSizeAndState(0, heightMeasureSpec, 0)
            )
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val childView = getChildAt(0)

        if (childView != null && childView.visibility != GONE) {
            childView.pivotX = 0f
            childView.pivotY = 0f
            childView.rotation = -90f

            val width = r - l
            val height = b - t
            childView.layout(0, height, height, width + height)
        }
    }

    fun addOnChangeListener(listener: Slider.OnChangeListener) {
        slider.addOnChangeListener(listener)
    }
}