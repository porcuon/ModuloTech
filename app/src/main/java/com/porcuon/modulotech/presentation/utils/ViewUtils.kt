package com.porcuon.modulotech.presentation.utils

import android.content.res.Resources

fun dpToPx(
    dp: Float,
    resources: Resources
): Float {
    return dp * resources.displayMetrics.density
}