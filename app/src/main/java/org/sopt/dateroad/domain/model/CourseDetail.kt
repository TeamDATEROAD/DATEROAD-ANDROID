package org.sopt.dateroad.domain.model

data class CourseDetail(
    val courseId: Int = 0,
    val imageList: List<String> = listOf(), // TODO: 나중에 String으로 변경
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
    val isMine: Boolean = false,
    val totalPoint: Int = 0
)
