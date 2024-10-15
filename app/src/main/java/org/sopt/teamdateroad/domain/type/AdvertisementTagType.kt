package org.sopt.teamdateroad.domain.type

import org.sopt.teamdateroad.domain.util.Advertisement

enum class AdvertisementTagType(
    val title: String
) {
    EDITOR(title = Advertisement.EDITOR),
    AD(title = Advertisement.AD),
    ABOUT(title = Advertisement.ABOUT),
    HOT(title = Advertisement.HOT);

    companion object {
        fun String.toAdvertisementTagTitle(): String = entries.find { it.name == this }?.title ?: ""
    }
}
