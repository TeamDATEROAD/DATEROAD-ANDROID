package org.sopt.teamdateroad.data.mapper.todata

import org.sopt.teamdateroad.data.dataremote.model.request.RequestTagsDto

fun List<String>.toData(): RequestTagsDto = RequestTagsDto(
    tag = this
)
