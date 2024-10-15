package org.sopt.teamdateroad.data.mapper.todata

import org.sopt.teamdateroad.data.dataremote.model.request.RequestTagDto

fun String.toData() = RequestTagDto(tag = this)
