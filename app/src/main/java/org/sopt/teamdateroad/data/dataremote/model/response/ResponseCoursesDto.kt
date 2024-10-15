package org.sopt.teamdateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCoursesDto(
    @SerialName("courses")
    val courses: List<ResponseCourseDto>
)
