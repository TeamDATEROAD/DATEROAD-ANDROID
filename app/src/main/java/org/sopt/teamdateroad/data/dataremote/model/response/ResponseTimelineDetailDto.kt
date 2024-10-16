package org.sopt.teamdateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTimelineDetailDto(
    @SerialName("dateId")
    val timelineId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("startAt")
    val startAt: String,
    @SerialName("city")
    val city: String,
    @SerialName("tags")
    val tags: List<ResponseTagDto>,
    @SerialName("date")
    val date: String,
    @SerialName("places")
    val places: List<ResponsePlaceDto>,
    @SerialName("dDay")
    val dDay: Int
)
