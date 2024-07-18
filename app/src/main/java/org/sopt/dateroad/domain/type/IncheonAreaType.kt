package org.sopt.dateroad.domain.type

import org.sopt.dateroad.domain.util.Incheon

enum class IncheonAreaType(
    val title: String
) {
    INCHEON_ENTIRE(
        title = Incheon.INCHEON_ENTIRE
    );

    companion object {
        fun String.toIncheonAreaTitle(): String = entries.find { it.name == this }?.title ?: ""
        fun String.toIncheonAreaType(): IncheonAreaType? = entries.find { it.name == this }
        fun String.fromTitleToIncheonAreaType(): IncheonAreaType? = entries.find { it.title == this }
    }
}
