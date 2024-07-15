package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCourseDto(
    @SerialName("courseId")
    val courseId: Int,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String,
    @SerialName("city")
    val city: String,
    @SerialName("cost")
    val cost: Int,
    @SerialName("duration")
    val duration: Float,
    @SerialName("like")
    val like: Int
)
