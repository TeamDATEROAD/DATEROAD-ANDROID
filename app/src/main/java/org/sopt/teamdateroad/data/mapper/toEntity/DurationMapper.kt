package org.sopt.teamdateroad.data.mapper.toEntity

import org.sopt.teamdateroad.data.dataremote.util.Duration

fun Float.toDuration(): String = if (this % 1.0 == 0.0) {
    "%.0f".format(this)
} else {
    "%.1f".format(this)
} + Duration.DURATION
