package org.sopt.dateroad.data.mapper.toEntity

import org.sopt.dateroad.data.dataremote.util.Duration

fun Float.toDuration(): String = "$this${Duration.DURATION}"
