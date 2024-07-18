package org.sopt.dateroad.domain.model

data class CourseDetail(
    val courseId: Int = 0,
    val images: List<String> = listOf(),
    val like: Int = 0,
    val totalTime: String = "",
    val date: String = "",
    val city: String = "",
    val title: String = "",
    val description: String = "",
    val places: List<Place> = listOf(),
    val totalCost: String = "",
    val tags: List<String> = listOf(),
    val isAccess: Boolean = false,
    val free: Int = 0,
    val totalPoint: Int = 0,
    val isCourseMine: Boolean = false,
    val isUserLiked: Boolean = false,
    val startAt: String = ""
)
