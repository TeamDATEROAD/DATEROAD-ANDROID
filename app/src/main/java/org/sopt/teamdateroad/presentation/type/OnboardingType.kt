
package org.sopt.teamdateroad.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.teamdateroad.R

enum class OnboardingType(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val subDescriptionRes: Int
) {
    FIRST(
        titleRes = R.string.onboarding_first_title,
        descriptionRes = R.string.onboarding_first_description,
        imageRes = R.drawable.img_onboarding_background1,
        subDescriptionRes = R.string.onboarding_first_sub_description
    ),
    SECOND(
        titleRes = R.string.onboarding_second_title,
        descriptionRes = R.string.onboarding_second_description,
        imageRes = R.drawable.img_onboarding_background2,
        subDescriptionRes = R.string.onboarding_second_sub_description
    ),
    THIRD(
        titleRes = R.string.onboarding_third_title,
        descriptionRes = R.string.onboarding_third_description,
        imageRes = R.drawable.img_onboarding_background3,
        subDescriptionRes = R.string.onboarding_third_sub_description
    )
}
