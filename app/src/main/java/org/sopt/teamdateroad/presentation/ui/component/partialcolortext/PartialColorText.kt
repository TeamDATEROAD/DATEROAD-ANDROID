package org.sopt.teamdateroad.presentation.ui.component.partialcolortext

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
        while (currentIndex < text.length) {
            val keywordIndex = keywords
                .map { keyword -> text.indexOf(keyword, currentIndex, ignoreCase = true) to keyword }
                .filter { it.first >= 0 }
                .minByOrNull { it.first }

            if (keywordIndex != null && keywordIndex.first >= 0) {
                append(text.substring(currentIndex, keywordIndex.first))
                withStyle(style = SpanStyle(color)) {
                    append(keywordIndex.second)
                }
                currentIndex = keywordIndex.first + keywordIndex.second.length
            } else {
                append(text.substring(currentIndex))
                break
            }
        }
    }
}
