package org.sopt.dateroad.data.mapper.todomain

import java.text.SimpleDateFormat
import java.util.Locale
import org.sopt.dateroad.data.dataremote.util.Date

fun String.toCourseDetailDate(): String = SimpleDateFormat(Date.INPUT_FORMAT, Locale.getDefault()).parse(this)?.let { SimpleDateFormat(Date.COURSE_DETAIL_OUTPUT_FORMAT, Locale.getDefault()).format(it) } ?: ""
fun String.toTimelineDetailDate(): String {
    val date = SimpleDateFormat(Date.INPUT_FORMAT, Locale.getDefault()).parse(this)
    return SimpleDateFormat(Date.TIMELINE_DETAIL_OUTPUT_FORMAT, Locale.getDefault()).format(date)
}
