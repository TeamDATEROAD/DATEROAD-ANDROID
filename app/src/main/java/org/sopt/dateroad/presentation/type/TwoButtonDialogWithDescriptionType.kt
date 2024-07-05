package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class TwoButtonDialogWithDescriptionType(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @StringRes val confirmButtonTextRes: Int,
    @StringRes val dismissButtonTextRes: Int
) {
    READ_COURSE(
        titleRes = R.string.two_button_dialog_with_description_read_course_title,
        descriptionRes = R.string.two_button_dialog_with_description_read_course_description,
        confirmButtonTextRes = R.string.dialog_check,
        dismissButtonTextRes = R.string.dialog_cancel
    ),
    POINT_LACK(
        titleRes = R.string.two_button_dialog_with_description_point_lack_title,
        descriptionRes = R.string.two_button_dialog_with_description_point_lack_description,
        confirmButtonTextRes = R.string.two_button_dialog_with_description_point_lack_confirm_button_text,
        dismissButtonTextRes = R.string.dialog_cancel
    ),
    FREE_READ(
        titleRes = R.string.two_button_dialog_with_description_free_read_title,
        descriptionRes = R.string.two_button_dialog_with_description_free_read_description,
        confirmButtonTextRes = R.string.dialog_check,
        dismissButtonTextRes = R.string.dialog_cancel
    ),
    DELETE_TIMELINE(
        titleRes = R.string.two_button_dialog_with_description_delete_timeline_title,
        descriptionRes = R.string.dialog_delete_schedule,
        confirmButtonTextRes = R.string.dialog_delete,
        dismissButtonTextRes = R.string.dialog_cancel
    ),
    DELETE_PAST(
        titleRes = R.string.two_button_dialog_with_description_delete_past_title,
        descriptionRes = R.string.dialog_delete_schedule,
        confirmButtonTextRes = R.string.dialog_delete,
        dismissButtonTextRes = R.string.dialog_cancel
    ),

    WITHDRAWAL(
        titleRes = R.string.two_button_dialog_with_description_withdrawal_title,
        descriptionRes = R.string.two_button_dialog_with_description_withdrawal_description,
        confirmButtonTextRes = R.string.dialog_cancel,
        dismissButtonTextRes = R.string.withdrawal
    )
}
