package org.sopt.dateroad.presentation.ui.component.partialcolortext

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun PartialColorText(text: String, keywords: List<String>, color: Color): AnnotatedString {
    return buildAnnotatedString {
        var currentIndex = 0
        keywords.forEach { keyword ->
            val keywordIndex = text.indexOf(keyword, currentIndex, ignoreCase = true)
            if (keywordIndex >= 0) {
                append(text.substring(currentIndex, keywordIndex))
                withStyle(style = SpanStyle(color)) {
                    append(text.substring(keywordIndex, keywordIndex + keyword.length))
                }
                currentIndex = keywordIndex + keyword.length
            }
        }
        append(text.substring(currentIndex))
    }
}
