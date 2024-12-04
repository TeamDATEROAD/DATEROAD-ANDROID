package org.sopt.teamdateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseEnrollCourseDto(
    @SerialName("userPoint")
    val userPoint: Int,
    @SerialName("userCourseCount")
    val userCourseCount: Long
)
