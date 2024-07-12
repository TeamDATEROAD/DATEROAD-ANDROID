package org.sopt.dateroad.presentation.util.context

import android.content.Context
import android.widget.Toast
import org.sopt.dateroad.presentation.type.DateTagType

fun Context.showToast(message: String, isShort: Boolean = true) {
    val duration = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    Toast.makeText(this, message, duration).show()
}

fun Context.mapTagsToDateTagType(tags: List<String>): List<DateTagType> {
    return tags.mapNotNull { tag ->
        DateTagType.entries.find { getString(it.titleRes) == tag }
    }
}
