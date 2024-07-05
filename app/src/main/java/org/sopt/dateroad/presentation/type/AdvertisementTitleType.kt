package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class AdvertisementTitleType(
    @StringRes val titleRes: Int
) {
    EDITOR_PICK(
        titleRes = R.string.advertisement_title_editor_pick
    ),
    ADVERTISEMENT(
        titleRes = R.string.advertisement_title_advertisement
    )
}