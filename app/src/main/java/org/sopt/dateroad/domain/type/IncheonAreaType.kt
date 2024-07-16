package org.sopt.dateroad.domain.type

import org.sopt.dateroad.domain.util.Incheon

enum class IncheonAreaType(
    val title: String
) {
    INCHEON_ENTIRE(
        title = Incheon.INCHEON_ENTIRE
    );
    companion object {
        fun fromString(value: String): IncheonAreaType? {
            return entries.find { it.name == value }
        }
    }
}
