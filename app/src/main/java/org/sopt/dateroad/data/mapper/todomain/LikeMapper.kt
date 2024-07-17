package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.util.Like

fun Int.toLike(): String = when {
    this < Like.THRESHOLD -> this.toString()
    else -> Like.LIKE_MAX
}
