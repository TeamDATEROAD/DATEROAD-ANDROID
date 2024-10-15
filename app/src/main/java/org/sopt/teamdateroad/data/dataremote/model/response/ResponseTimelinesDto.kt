package org.sopt.teamdateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTimelinesDto(
    @SerialName("dates")
    val timelines: List<ResponseTimelineDto>
)
