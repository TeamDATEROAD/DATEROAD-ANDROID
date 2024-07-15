package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.util.Point

fun Int.toPoint(): String = "$this${Point.POINT}"