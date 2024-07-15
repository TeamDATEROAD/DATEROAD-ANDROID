package org.sopt.dateroad.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseCoursesDto(
    @SerialName("courses")
    val courses: List<ResponseCourseDto>
)
