package org.sopt.dateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class OnboardingType(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
) {
    FIRST(
        titleRes = R.string.onboarding_first_title,
        descriptionRes = R.string.onboarding_first_description,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    SECOND(
        titleRes = R.string.onboarding_second_title,
        descriptionRes = R.string.onboarding_second_description,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    THIRD(
        titleRes = R.string.onboarding_third_title,
        descriptionRes = R.string.onboarding_third_description,
        imageRes = R.drawable.ic_launcher_foreground
    )
}