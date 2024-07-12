package org.sopt.dateroad.domain.model

data class CourseDetail(
    val courseId: Int,
    val imageList: List<Int>, // TODO: 나중에 String으로 변경
    val like: Int,
    val totalTime: Int,
    val date: String,
    val city: String,
    val title: String,
    val description: String,
    val places: List<Place>,
    val totalCost: String,
    val tags: List<String>,
    val isAccess: Boolean,
    val free: Int,
    val isMine: Boolean,
    val totalPoint: Int
)
