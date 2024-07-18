package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestTagDto

fun String.toData() = RequestTagDto(tag = this)
