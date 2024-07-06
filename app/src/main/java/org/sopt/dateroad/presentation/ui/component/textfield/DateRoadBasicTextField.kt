package org.sopt.dateroad.presentation.ui.component.textfield

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.dateroad.ui.theme.DATEROADTheme

@Composable
fun DateRoadBasicTextField(
    modifier: Modifier = Modifier,
    title: String? = null,
    placeholder: String = "",

    value: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit = { _ -> },
) {
    Text(
        text = title,
        color
    )
}

@Preview
@Composable
fun DateRoadBasicTextFieldPreview() {
    DATEROADTheme {
        DateRoadBasicTextField(
            title = "총 비용을 입력해 주세요"
        )
    }
}