package org.sopt.dateroad.presentation.ui.component.bottomsheet.model

import org.sopt.dateroad.presentation.ui.component.numberpicker.state.PickerState

data class Picker(
    val items: List<String>,
    val pickerState: PickerState = PickerState()
)
