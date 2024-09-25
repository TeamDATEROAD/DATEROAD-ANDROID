package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import androidx.compose.ui.text.TextStyle
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.defaultDateRoadTypography

enum class DateChipGroupType(
    @StringRes val titleRes: Int,
    val titleTextStyle: TextStyle,
    val maxSize: Int
) {
    PROFILE(
        titleRes = R.string.date_chip_group_profile,
        titleTextStyle = defaultDateRoadTypography.bodyBold15,
        maxSize = 3
    ),
    ENROLL(
        titleRes = R.string.date_chip_group_enroll_course,
        titleTextStyle = defaultDateRoadTypography.bodySemi15,
        maxSize = 3
    )
}
