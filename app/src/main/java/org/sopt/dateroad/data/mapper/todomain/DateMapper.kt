package org.sopt.dateroad.data.mapper.todomain

import java.text.SimpleDateFormat
import java.util.Locale
import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestDateDto
import org.sopt.dateroad.data.dataremote.util.Date

fun String.toCourseDetailDate(): String = SimpleDateFormat(Date.INPUT_FORMAT, Locale.getDefault()).parse(this)?.let { SimpleDateFormat(Date.COURSE_DETAIL_OUTPUT_FORMAT, Locale.getDefault()).format(it) } ?: ""
fun String.toBasicDates(): String = SimpleDateFormat(Date.INPUT_FORMAT, Locale.getDefault()).parse(this)?.let { SimpleDateFormat(Date.DATE_OUTPUT_FORMAT, Locale.getDefault()).format(it) } ?: ""
fun Int.toDDayString(): String = when {
    this > 0 -> "${Date.D_DAY_OUTPUT_FORMAT}$this"
    this == 0 -> Date.D_DAY_DEFAULT_LABEL
    else -> ""
}
fun String.toStartAtString(): String = "$this${Date.NEAREST_DATE_START_OUTPUT_FORMAT}"
fun String.toAdvertisementDetailDate(): String = SimpleDateFormat(Date.INPUT_FORMAT, Locale.getDefault()).parse(this)?.let { SimpleDateFormat(Date.ADVERTISEMENT_DETAIL_OUTPUT_FORMAT, Locale.getDefault()).format(it) } ?: ""
fun ResponseNearestDateDto.toFormattedDate(): String = String.format(Date.MAIN_DATE_OUTPUT_FORMAT, this.month, this.day)
