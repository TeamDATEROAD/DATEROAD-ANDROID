package org.sopt.dateroad.presentation.type

import androidx.compose.runtime.Composable
import org.sopt.dateroad.presentation.util.EnrollScreenPosition

enum class EnrollScreenType(
    val position: Int
) {
    FIRST(
        position = EnrollScreenPosition.FIRST,
    ),
    SECOND(
        position = EnrollScreenPosition.SECOND,
    ),
    THIRD(
        position = EnrollScreenPosition.THIRD,
    )
}
