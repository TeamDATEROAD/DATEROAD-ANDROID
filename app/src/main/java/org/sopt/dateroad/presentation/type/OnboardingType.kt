package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class OnboardingType(
    @StringRes titleRes: Int,
    @StringRes description: Int,
    @DrawableRes image: Int
) {
    FIRST(
        titleRes = R.string.onboarding_first_title,
        description = R.string.onboarding_first_description,
        image = R.drawable.ic_launcher_foreground
    ),
    SECOND(
        titleRes = R.string.onboarding_second_title,
        description = R.string.onboarding_second_description,
        image = R.drawable.ic_launcher_foreground
    ),
    THIRD(
        titleRes = R.string.onboarding_third_title,
        description = R.string.onboarding_third_description,
        image = R.drawable.ic_launcher_foreground
    )
}