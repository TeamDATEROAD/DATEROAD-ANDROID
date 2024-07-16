package org.sopt.dateroad.presentation.util.context

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String, isShort: Boolean = true) {
    val duration = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    Toast.makeText(this, message, duration).show()
}
