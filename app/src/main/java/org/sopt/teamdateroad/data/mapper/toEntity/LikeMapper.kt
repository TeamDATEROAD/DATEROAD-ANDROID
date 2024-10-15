package org.sopt.teamdateroad.data.mapper.toEntity

import org.sopt.teamdateroad.data.dataremote.util.Like

fun Int.toLike(): String = when {
    this < Like.THRESHOLD -> this.toString()
    else -> Like.LIKE_MAX
}
