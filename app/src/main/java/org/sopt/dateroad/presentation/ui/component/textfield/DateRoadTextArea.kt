package org.sopt.dateroad.presentation.ui.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadTextArea(
    modifier: Modifier = Modifier,
    title: String? = null,
    placeholder: String = "",
    minLength: Int = 200,
    value: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit = { _ -> },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        title?.let {
            Text(
                text = it,
                color = DateRoadTheme.colors.black,
                style = DateRoadTheme.typography.bodyBold17
            )
            Spacer(
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(279.dp)
                .background(color = DateRoadTheme.colors.gray100, shape = RoundedCornerShape(14.dp))
                .padding(vertical = 16.dp, horizontal = 14.dp),
            value = value,
            onValueChange = onValueChange,
            cursorBrush = SolidColor(DateRoadTheme.colors.purple500),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            textStyle = DateRoadTheme.typography.bodyMed13Context,
            decorationBox = { innerTextField ->
                innerTextField()
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = DateRoadTheme.colors.gray300,
                        style = DateRoadTheme.typography.bodyMed13Context
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            text = stringResource(id = R.string.text_area_length_content, value.length, minLength),
            color = DateRoadTheme.colors.gray300,
            style = DateRoadTheme.typography.bodyMed13
        )
    }
}

@Preview
@Composable
fun DateRoadBasicTextAreaPreview() {
    DATEROADTheme {
        var text by remember { mutableStateOf("") }

        DateRoadTextArea(
            title = "코스에 대한 설명을 적어주세요",
            placeholder = "데이트 내용을 입력해 주세요\n예약 정보, 웨이팅 정보, 꿀팁 등을 작성해 주세요.\n(최소 200자)",
            value = text,
            onValueChange = { newValue ->
                text = newValue
            }
        )
    }
}
