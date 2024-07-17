package org.sopt.dateroad.domain.type

import org.sopt.dateroad.domain.util.Incheon

enum class IncheonAreaType(
    val title: String
) {
    INCHEON_ENTIRE(
        title = Incheon.INCHEON_ENTIRE
    );

    fun String.toIncheonAreaType() = GyeonggiAreaType.entries.find { it.name == this }?.name
}
