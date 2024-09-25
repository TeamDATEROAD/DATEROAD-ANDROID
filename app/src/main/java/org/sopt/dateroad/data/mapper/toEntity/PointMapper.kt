package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.data.dataremote.util.Point

fun Int.toPoint(): String = this.toString() + Point.POINT

fun Int.toGainedPoint(): String = Point.GAINED + this + Point.POINT

fun Int.toUsedPoint(): String = Point.USED + this + Point.POINT
