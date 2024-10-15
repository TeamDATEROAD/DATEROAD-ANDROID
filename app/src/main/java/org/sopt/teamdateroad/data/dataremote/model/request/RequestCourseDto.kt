package org.sopt.teamdateroad.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCourseDto(
    @SerialName("title")
    val title: String,
    @SerialName("date")
    val date: String,
    @SerialName("startAt")
    val startAt: String,
    @SerialName("country")
    val country: String,
    @SerialName("city")
    val city: String,
    @SerialName("description")
    val description: String,
    @SerialName("cost")
    val cost: Int
)
