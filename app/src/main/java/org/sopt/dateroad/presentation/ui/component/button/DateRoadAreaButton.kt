package org.sopt.dateroad.presentation.ui.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.R
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadAreaButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    textContent: String,
    onClick: () -> Unit = {}
) {
    DateRoadButton(
        modifier = modifier
            .fillMaxWidth(),
        backgroundColor = DateRoadTheme.colors.gray100,
        borderColor = if (isSelected) DateRoadTheme.colors.purple600 else Color.Unspecified,
        borderWidth = 1.dp,
        cornerRadius = 10.dp,
        paddingVertical = 6.dp,
        paddingHorizontal = 12.dp,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = textContent,
                color = if (isSelected) DateRoadTheme.colors.purple600 else DateRoadTheme.colors.gray400,
                style = DateRoadTheme.typography.bodyMed13
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(painter = painterResource(id = R.drawable.ic_area_dropdown), contentDescription = null, tint = if (isSelected) DateRoadTheme.colors.purple600 else DateRoadTheme.colors.gray400)
        }
    }
}

@Preview
@Composable
fun DateRoadAreaButtonPreview() {
    DATEROADTheme {
        DateRoadAreaButton(
            textContent = "건대/성수/왕십리",
            isSelected = true
        )
    }
}
