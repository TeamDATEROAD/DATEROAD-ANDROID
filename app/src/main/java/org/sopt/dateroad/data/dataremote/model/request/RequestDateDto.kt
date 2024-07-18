package org.sopt.dateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestDateDto(
    @SerialName("title")
    val title: String,
    @SerialName("date")
    val date: String,
    @SerialName("startAt")
    val startAt: String,
    @SerialName("tags")
    val tags: List<RequestTagDto>,
    @SerialName("country")
    val country: String,
    @SerialName("city")
    val city: String,
    @SerialName("places")
    val places: List<RequestPlaceDto>
)
