package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.util.Duration

fun Float.toDuration(): String = "$this${Duration.DURATION}"
