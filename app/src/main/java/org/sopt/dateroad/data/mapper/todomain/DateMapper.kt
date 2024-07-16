package org.sopt.dateroad.data.mapper.todomain

import java.text.SimpleDateFormat
import java.util.Locale
import org.sopt.dateroad.data.dataremote.util.Date
import org.sopt.dateroad.domain.type.GyeonggiAreaType
import org.sopt.dateroad.domain.type.IncheonAreaType
import org.sopt.dateroad.domain.type.SeoulAreaType

fun String.toCourseDetailDate(): String = SimpleDateFormat(Date.INPUT_FORMAT, Locale.getDefault()).parse(this)?.let { SimpleDateFormat(Date.COURSE_DETAIL_OUTPUT_FORMAT, Locale.getDefault()).format(it) } ?: ""
fun String.toTimelineDate(): String {
    val date = SimpleDateFormat(Date.INPUT_FORMAT, Locale.getDefault()).parse(this)
    return SimpleDateFormat(Date.TIMELINE_DETAIL_OUTPUT_FORMAT, Locale.getDefault()).format(date)
}
fun String.toAreaTitle(): String {
    return SeoulAreaType.fromString(this)?.title
        ?: GyeonggiAreaType.fromString(this)?.title
        ?: IncheonAreaType.fromString(this)?.title
        ?: this
}
