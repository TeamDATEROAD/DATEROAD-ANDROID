package org.sopt.dateroad.presentation.type

import androidx.compose.runtime.Composable
import org.sopt.dateroad.presentation.ui.enroll.EnrollThirdScreen
import org.sopt.dateroad.presentation.util.EnrollScreenPosition

enum class EnrollScreenType(
    val position: Int,
    val screen: @Composable () -> Unit
) {
    FIRST(
        position = EnrollScreenPosition.FIRST,
        screen = { }
    ),
    SECOND(
        position = EnrollScreenPosition.SECOND,
        screen = { }
    ),
    THIRD(
        position = EnrollScreenPosition.THIRD,
        screen = {  }
    )
}
