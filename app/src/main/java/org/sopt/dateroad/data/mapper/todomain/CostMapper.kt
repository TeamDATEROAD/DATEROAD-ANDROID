package org.sopt.dateroad.data.mapper.todomain

import java.text.NumberFormat
import java.util.Locale
import org.sopt.dateroad.data.dataremote.util.Cost

fun Int.toCost(): String = "${NumberFormat.getNumberInstance(Locale.KOREA).format(this)}${Cost.COST}"
