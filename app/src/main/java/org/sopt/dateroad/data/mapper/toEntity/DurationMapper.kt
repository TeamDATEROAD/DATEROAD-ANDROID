package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.data.dataremote.util.Duration

fun Float.toDuration(): String {
    val formattedDuration = if (this % 1.0 == 0.0) {
        "%.0f".format(this)
    } else {
        "%.1f".format(this)
    }
    return "$formattedDuration${Duration.DURATION}"
}
