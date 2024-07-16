package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCourseDetailDto(
    @SerialName("courseId")
    val courseId: Int,
    @SerialName("images")
    val images: List<ResponseImageDto>,
    @SerialName("like")
    val like: Int,
    @SerialName("totalTime")
    val totalTime: Float,
    @SerialName("date")
    val date: String,
    @SerialName("city")
    val city: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("startAt")
    val startAt: String,
    @SerialName("places")
    val places: List<ResponsePlaceDto>,
    @SerialName("totalCost")
    val totalCost: Int,
    @SerialName("tags")
    val tags: List<ResponseTagDto>,
    @SerialName("isAccess")
    val isAccess: Boolean,
    @SerialName("free")
    val free: Int,
    @SerialName("totalPoint")
    val totalPoint: Int,
    @SerialName("isCourseMine")
    val isCourseMine: Boolean,
    @SerialName("isUserLiked")
    val isUserLiked: Boolean
)
