package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.util.Date
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toCourseDetailDate(): String = SimpleDateFormat(Date.INPUT_FORMAT, Locale.getDefault()).parse(this)?.let { SimpleDateFormat(Date.COURSE_DETAIL_OUTPUT_FORMAT, Locale.getDefault()).format(it) } ?: ""

fun String.toAdvertisementDetailDate(): String = SimpleDateFormat(Date.INPUT_FORMAT, Locale.getDefault()).parse(this)?.let { SimpleDateFormat(Date.ADVERTISEMENT_DETAIL_OUTPUT_FORMAT, Locale.getDefault()).format(it) } ?: ""