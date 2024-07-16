package org.sopt.dateroad.domain.type

enum class SortByType(
    val sortByQuery: String
) {
    POPULAR(
        sortByQuery = "POPULAR"
    ),
    LATEST(
        sortByQuery = "LATEST"
    )
}
