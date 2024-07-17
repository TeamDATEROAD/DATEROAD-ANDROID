package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestTagsDto

fun List<String>.toData(): RequestTagsDto = RequestTagsDto(
    tag = this
)